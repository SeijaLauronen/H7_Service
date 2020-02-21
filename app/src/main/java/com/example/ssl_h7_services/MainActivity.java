package com.example.ssl_h7_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

//Laita entityyn implements serialisable

public class MainActivity extends AppCompatActivity {
    private String string;


    //Date d;   got to implementation, täältä saat selville serialized -onko vai ei ja syntaksin

    public class Kuuntelija extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
        String str =intent.getAction();
        String strToDB;

            if (str.equals(Intent.ACTION_SCREEN_ON)){
                Toast.makeText(context,"Avattiin",Toast.LENGTH_LONG).show();
                strToDB = "Avattiin";

            } else {
                Toast.makeText(context,"Suljettiin",Toast.LENGTH_LONG).show();
                strToDB = "Suljettiin";
            }


            PalveluEntity palveluEntity = new PalveluEntity();
            palveluEntity.teksti = strToDB;

            //TODO: Tämä pvm säätö olis järkevämpi tehdä muualla
            Calendar calendar = new GregorianCalendar();

            int year       = calendar.get(Calendar.YEAR);
            int month      = calendar.get(Calendar.MONTH) +1; //HUOM! java aloittaa kk  numeroinnin 0:sta
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
            int minute     = calendar.get(Calendar.MINUTE);
            int seconds     = calendar.get(Calendar.SECOND);

            String strYear = Integer.toString(year);
            String strMonth = Integer.toString(month);
            if (strMonth.length()==1) strMonth ="0"+strMonth; //0 eteen, jos 1-numeroinen kk
            String strDayOfMonth = Integer.toString(dayOfMonth);
            if (strDayOfMonth.length()==1) strDayOfMonth ="0"+strDayOfMonth; //0 eteen, jos 1-numeroinen pv
            String strHourOfDay = Integer.toString(hourOfDay);
            if (strHourOfDay.length()==1) strHourOfDay ="0"+strHourOfDay; //0 eteen, jos 1-numeroinen kk
            String strMinute =Integer.toString(minute);
            if (strMinute.length()==1) strMinute ="0"+strMinute; //0 eteen, jos 1-numeroinen kk

            String klo = strHourOfDay +":" + strMinute +":"+seconds;
            palveluEntity.pvm = strYear + "-" + strMonth +"-" + strDayOfMonth + " (" + klo + ")";

            DBHandler dbHandler = new DBHandler();
            dbHandler.writeToDB(palveluEntity,getApplicationContext());

        }
    }

    Kuuntelija kuuntelija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kuuntelija = new Kuuntelija();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(kuuntelija,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(kuuntelija);
    }

    //Tämä tehty ohjeen mukaiseen eventtiin, en saanut overridettavia esille CRTL + välilyönnillä
    @Override
    protected void onResume() {

        TextView textViewResult; //tähän ei saanut laittaa private
        textViewResult=findViewById((R.id.textViewResult));
        String teksti="";

        super.onResume();
        DBHandler dbHandler = new DBHandler();
        List<PalveluEntity> tResults = dbHandler.fetchFromDB(getApplicationContext());
        for (int i =0; i<tResults.size();i++){
             teksti += tResults.get(i).pvm+": ";
            teksti += tResults.get(i).teksti+"\n ";
        }

        textViewResult.setText(teksti);

    }
}
