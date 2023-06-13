package com.example.quick_note.controllers;

import android.content.Context;
import android.widget.Toast;

import com.example.quick_note.models.NotaListAdapter;
import com.example.quick_note.models.Nota;
import com.example.quick_note.models.NotaDao;
import com.example.quick_note.models.NotaNombreAdapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class NotaController {
    private  Context context;

    public NotaController(Context context){
        this.context = context;
    }

    //muestra las notas en la aplicacion
    public void mostrarNotas(RecyclerView recyclerView, NotaListAdapter adapter) {
        // Creamos una instancia de NotaDao
        NotaDao notaDao = new NotaDao(this.context).obtenerNotas(new NotaDao.CallBack() {
            @Override
            public void onSuccess(List<Nota> notas) {
                recyclerView.setAdapter(adapter);
                adapter.setNotas(notas);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(recyclerView.getContext(), "Falló capo", Toast.LENGTH_LONG).show();
            }
        });
    }

}
