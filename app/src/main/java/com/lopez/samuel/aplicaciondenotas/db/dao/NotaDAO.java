package com.lopez.samuel.aplicaciondenotas.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.lopez.samuel.aplicaciondenotas.db.entity.NotaEntity;

import java.util.List;

@Dao
public interface NotaDAO {

    @Insert
    void insert(NotaEntity nota);

    @Update
    void update(NotaEntity nota);

    @Query("DELETE FROM notas")
    void deleteAll();

    @Query("DELETE FROM notas WHERE id = :notaId")
    void deleteById(int notaId);

    @Query("SELECT * FROM notas ORDER BY titulo ASC")
    LiveData<List<NotaEntity>> getAll();

    @Query("SELECT * FROM notas WHERE favorita LIKE 1")
    LiveData<List<NotaEntity>> getAllFavoritas();
}
