package com.example.quick_note;

import android.content.Intent;
import android.os.Bundle;

import com.example.quick_note.controllers.NotaController;
import com.example.quick_note.controllers.UsuarioController;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quick_note.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityLoginBinding binding;
    private UsuarioController usuarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Button entrar = findViewById(R.id.entrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuarioController = new UsuarioController(LoginActivity.this);

                //obtiene los datos que se encuentran en los editText
                int var = 0;
                EditText usuario = findViewById(R.id.usuario);
                EditText contraseña = findViewById(R.id.contrasena);

                //asigna los datos obtenidos a unas variables de tipo string
                String nombreUsuario = usuario.getText().toString().trim();
                String contraseñaUsuario = contraseña.getText().toString().trim();


                //verifica que el nombre de usuario y contraseña no esten vacios
                if (nombreUsuario.isEmpty() || contraseñaUsuario.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Especifica el usuario y contraseña", Toast.LENGTH_SHORT).show();
                } else if ((nombreUsuario.equals("admin")) && (contraseñaUsuario.equals("admin"))) {
                    //limpia los editText
                    usuario.setText("");
                    contraseña.setText("");
                    //muestra un mensaje de que el usuario es correcto
                    Toast.makeText(LoginActivity.this, "Usuario Correcto", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    //mensaje si el usuario es incorrecto
                    Toast.makeText(LoginActivity.this, "Usuario Incorrecto", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}