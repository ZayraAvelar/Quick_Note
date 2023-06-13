package com.example.quick_note;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quick_note.controllers.NotaController;
import com.example.quick_note.models.NotaListAdapter;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotaController notaController = new NotaController(this);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final NotaListAdapter adapter = new NotaListAdapter(this);
        recyclerView.setAdapter(adapter);
        notaController.mostrarNotas(recyclerView, adapter);

    }

    //muestra la pagina de agregar nota
    public void agregar(View view) {
        Intent intent = new Intent(this, Agregar.class);
        startActivity(intent);
    }


    //actualiza los datos
    @Override
    public void onRefresh() {

        actualizarDatos();
    }

    //actualiza los datos
    private void actualizarDatos() {
        swipeRefreshLayout.setRefreshing(false);
        recreate();
    }

}