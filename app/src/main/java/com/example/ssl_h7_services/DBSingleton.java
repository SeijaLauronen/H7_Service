package com.example.ssl_h7_services;


import android.content.Context;
import android.support.v4.app.INotificationSideChannel;

import androidx.room.Room;

public class DBSingleton {

    private static DBPalvelu INSTANCE; //Huom, tähän piti laittaa static
    public static DBPalvelu getInstance(Context context){

        if (INSTANCE == null){
            INSTANCE =Room.databaseBuilder(context,DBPalvelu.class,DBPalvelu.NIMI)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
            return INSTANCE;
        } else {
            //if (this.INSTANCE == null)
            //{this.INSTANCE= Room.databaseBuilder().build();}
            //return null;
            return INSTANCE;
        }
    }


}
