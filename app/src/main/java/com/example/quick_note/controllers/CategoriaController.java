package com.example.quick_note.controllers;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quick_note.Agregar;
import com.example.quick_note.R;
import com.example.quick_note.models.Categoria;
import com.example.quick_note.models.CategoriaDao;
import com.example.quick_note.models.Nota;
import com.example.quick_note.models.NotaDao;
import com.example.quick_note.models.NotaListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

    private Context context;
    private Agregar view;
    private Spinner spinner;

    private ArrayList<String> spinnerDataList;
    private EditText editText;

    public CategoriaController(Context context, Agregar view){
        this.context = context;
        this.view = view;
        spinner = view.findViewById(R.id.spinner);
        editText = view.findViewById(R.id.editTextText);

    }


    //permite mostrar las categorias en el spinner
    public void ponerCategorias( ){
        CategoriaDao categoriaDao = new CategoriaDao(context).obtenerCategorias(new CategoriaDao.CallBack() {
            @Override
            public void onSuccess(List<Categoria> categorias) {
                List<String> nombresCategorias = new ArrayList<>();

                //recorre las categoriasa
                for (Categoria categoria : categorias) {

                    //va agregando las categorias a la lista nombreCategorias
                    nombresCategorias.add(categoria.getNombreCategoria());
                }

                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, nombresCategorias);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = parent.getItemAtPosition(position).toString();
                        // Realiza las acciones necesarias con el elemento seleccionado
                        // ...
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // No se seleccionó ningún elemento
                    }
                });
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }


    //agrega la nota
    public void agregarNota(Nota miNota) {
        NotaDao notaDao = new NotaDao(context).subirNota(miNota);
    }


    //edita la nota
    public void editarNota(Nota miNota) {
        NotaDao notaDao = new NotaDao(context).editarNota(miNota);
    }

    //guarda la categoria
    public void guardarCategoria(String nombre){
        CategoriaDao categoriaDao =  new CategoriaDao(context).subirCategoria(nombre);
    }
}
