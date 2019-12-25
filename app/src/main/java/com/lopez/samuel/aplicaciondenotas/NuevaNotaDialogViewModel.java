package com.lopez.samuel.aplicaciondenotas;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.lopez.samuel.aplicaciondenotas.db.entity.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;

    public NuevaNotaDialogViewModel(Application application){
        super(application);

        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll();
    }

    // el fragment que necesita recibir la nueva lista de notas
    public LiveData<List<NotaEntity>> getAllNotas() {return allNotas;}

    //el fragment que inserte una nueva nota, debera comunicarlo a este viewmodel
    public void insertarNota(NotaEntity nuevaNotaEntitiy){
        notaRepository.insert(nuevaNotaEntitiy);

    }
}
