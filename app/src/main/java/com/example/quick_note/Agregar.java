package com.example.quick_note;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quick_note.controllers.CategoriaController;
import com.example.quick_note.models.Nota;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.StatementEvent;

public class Agregar extends AppCompatActivity {

    private CategoriaController categoriaController;
    private EditText titulo;
    private EditText Descripcion;
    private int idAsig;
    private String tituloAsing;
    private String DescripAsig;
    private int idCategoria;


    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        Intent intent = getIntent();

        idCategoria =  intent.getIntExtra("idCat", 0);
        System.out.println(idCategoria);

        idAsig = intent.getIntExtra("idNota", 0);
        System.out.println(idAsig);
        tituloAsing = intent.getStringExtra("tNota");
        if (tituloAsing == null) {
            tituloAsing = "";
        }

        DescripAsig = intent.getStringExtra("descNota");
        if (DescripAsig == null) {
            DescripAsig = "";
        }

        titulo = (EditText) findViewById(R.id.editTextText);
        Descripcion = (EditText) findViewById(R.id.editTextText2);
        Spinner spinner =  findViewById(R.id.spinner);

        categoriaController = new CategoriaController(this, this);
        categoriaController.ponerCategorias();

        //Crea un alert que deja agregar categorias
        builder = new AlertDialog.Builder(this);

        titulo.setText(tituloAsing);
        Descripcion.setText(DescripAsig);
        spinner.setSelection(idCategoria - 1);


    }

    public void agregarNota(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        int selectedItemIndex = spinner.getSelectedItemPosition();

        Nota miNota = new Nota();


        String nombre = titulo.getText().toString();
        String descrips = Descripcion.getText().toString();

        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = formato.format(fechaActual);

        if(nombre.isEmpty() ||  descrips.isEmpty()){
            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else {

            miNota.setFechaNota(fechaFormateada);
            miNota.setCatNota(selectedItemIndex + 1);
            miNota.setNombreNota(nombre);
            miNota.setDescNota(descrips);


            if (idAsig == 0) {
                categoriaController.agregarNota(miNota);
                titulo.setText("");
                Descripcion.setText("");
                spinner.setSelection(0);
            } else {
                miNota.setIdNota(idAsig);
                categoriaController.editarNota(miNota);
            }



            Toast.makeText(getApplicationContext(), "¡Guardado!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

    public void agregarCategoria(View view) {
        builder.setTitle("Agregar Categoria");
        // Crear el EditText dentro del AlertDialog
        final EditText editText = new EditText(this);
        builder.setView(editText);

        //botón positivo
        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nombreCat = String.valueOf(editText.getText());
                categoriaController.guardarCategoria(nombreCat);
                recreate();
            }
        });
        //botón negativo
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        // Crear y mostrar el AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}