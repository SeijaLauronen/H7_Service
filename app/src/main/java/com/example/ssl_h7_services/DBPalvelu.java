package com.example.ssl_h7_services;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities ={PalveluEntity.class} , version=1)
public abstract class DBPalvelu extends RoomDatabase {

    public static final String NIMI= "DBPALVELU";
    public  abstract PalveluDao palveluDao() ;
}
