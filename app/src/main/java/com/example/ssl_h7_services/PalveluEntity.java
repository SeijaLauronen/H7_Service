package com.example.ssl_h7_services;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.io.Serializable;

@Entity
public class PalveluEntity implements Serializable {
//public class PalveluEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String teksti;
    public String pvm;
}
