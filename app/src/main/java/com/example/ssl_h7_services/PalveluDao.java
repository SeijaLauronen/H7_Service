package com.example.ssl_h7_services;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PalveluDao {
    @Query("Select * from PalveluEntity order by id desc")
    List<PalveluEntity> getAllInDescenfingOrder();
    @Insert
    void InsertTaulu(PalveluEntity taulu);
    @Delete
    void DeleteTaulusta(PalveluEntity taulu);

}
