package com.lopez.samuel.aplicaciondenotas.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.lopez.samuel.aplicaciondenotas.NuevaNotaDialogFragment;
import com.lopez.samuel.aplicaciondenotas.NuevaNotaDialogViewModel;
import com.lopez.samuel.aplicaciondenotas.R;
import com.lopez.samuel.aplicaciondenotas.db.entity.NotaEntity;

import java.util.ArrayList;
import java.util.List;


public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private MyNotaRecyclerViewAdapter adapterNotas;
    private List<NotaEntity> notaList;
    private NuevaNotaDialogViewModel notaViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        // Indicamos que el fragment tiene un menú de opciones propio
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
            }
            // llenar la nota
            notaList = new ArrayList<>();
            //notaList.add(new NotaEntity("Lista de comprar", "Es necesario comprar el pan", true, R.color.colorPrimary));
            //notaList.add(new NotaEntity("Lista de comprar", "Es necesario comprar el pan jdjdjdjd", true, android.R.color.background_dark));
            //notaList.add(new NotaEntity("Lista de comprar", "Es necesario comprar el pan", true, android.R.color.holo_blue_bright));


            adapterNotas = new MyNotaRecyclerViewAdapter(notaList, getActivity());
            recyclerView.setAdapter(adapterNotas);
            
            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        notaViewModel = ViewModelProviders.of(getActivity())
                .get(NuevaNotaDialogViewModel.class);
        notaViewModel.getAllNotas().observe(getActivity(), new Observer<List<NotaEntity>>() {
            @Override
            public void onChanged(@Nullable List<NotaEntity> notaEntities) {
                adapterNotas.setNuevasNotas(notaEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_nota_fragment, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_add_nota:
                mostrarDialogNuevaNota();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarDialogNuevaNota() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        NuevaNotaDialogFragment dialogNuevaNota = new NuevaNotaDialogFragment();
        dialogNuevaNota.show(fm, "NuevaNotaDialogFragment");
    }
}
