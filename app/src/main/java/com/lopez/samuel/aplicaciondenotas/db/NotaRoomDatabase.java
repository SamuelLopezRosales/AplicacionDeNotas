package com.lopez.samuel.aplicaciondenotas.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.lopez.samuel.aplicaciondenotas.db.dao.NotaDAO;
import com.lopez.samuel.aplicaciondenotas.db.entity.NotaEntity;

@Database(entities = {NotaEntity.class}, version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {

    public abstract NotaDAO notaDAO();

    private static volatile NotaRoomDatabase INSTANCE;

    public static NotaRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (NotaRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            NotaRoomDatabase.class, "notas_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
