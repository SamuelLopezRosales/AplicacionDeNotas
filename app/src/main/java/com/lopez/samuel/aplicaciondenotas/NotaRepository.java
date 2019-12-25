package com.lopez.samuel.aplicaciondenotas;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.lopez.samuel.aplicaciondenotas.db.NotaRoomDatabase;
import com.lopez.samuel.aplicaciondenotas.db.dao.NotaDAO;
import com.lopez.samuel.aplicaciondenotas.db.entity.NotaEntity;

import java.util.List;

public class NotaRepository {

    private NotaDAO notaDAO;
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allNotasFavoritas;

    public NotaRepository(Application application) {
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDAO = db.notaDAO();
        allNotas = notaDAO.getAll();
        allNotasFavoritas = notaDAO.getAllFavoritas();

    }

    public LiveData<List<NotaEntity>> getAll() { return allNotas; }

    public LiveData<List<NotaEntity>> getAllFavs() { return allNotasFavoritas; }

    /** Para insertar no se puede hacer en 1 er plano se necesita
     * una clase AsyncTask
     */

    public void insert(NotaEntity nota){
        new insertAsyncTask(notaDAO).execute(nota);
    }

    private static class insertAsyncTask extends AsyncTask<NotaEntity,Void, Void>{
        private NotaDAO notaDAOAsyncTask;

        insertAsyncTask(NotaDAO dao){
            notaDAOAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDAOAsyncTask.insert(notaEntities[0]);
            return null;
        }
    }
}
