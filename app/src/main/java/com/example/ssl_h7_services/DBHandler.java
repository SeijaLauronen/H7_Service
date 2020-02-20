package com.example.ssl_h7_services;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DBHandler {
    public void writeToDB(PalveluEntity palveluEntity, Context context){
        PalveluDao palveluDao;
        DBPalvelu dbPalvelu= DBSingleton.getInstance(context);
        palveluDao=dbPalvelu.palveluDao();
        palveluDao.InsertTaulu(palveluEntity);
    }

    public List<PalveluEntity> fetchFromDB(Context context){

        //PalveluEntity palveluEntity = new PalveluEntity();
        PalveluDao palveluDao;
        DBPalvelu dbPalvelu= DBSingleton.getInstance(context);
        palveluDao=dbPalvelu.palveluDao();

        List<PalveluEntity> tResults=palveluDao.getAllInDescenfingOrder();

        return tResults;
    }

}
