package com.example.quick_note.controllers;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quick_note.LoginActivity;
import com.example.quick_note.models.Usuario;
import com.example.quick_note.models.UsuarioDao;

import java.util.List;


public class UsuarioController {

    private Context context;

    public UsuarioController(Context context) {
        this.context = context;
    }

    public void comprobarUsuario(EditText usuario, EditText contraseña, final Callback callback) {
        String nombreUsuario = usuario.getText().toString();
        String contraseñaUsuario = contraseña.getText().toString();

        UsuarioDao usuarioDao = new UsuarioDao(this.context);
        usuarioDao.obtenerUsuarios(new UsuarioDao.CallBack() {

            @Override
            public void onSuccess(List<Usuario> usuarios) {
                boolean usuarioEncontrado = false;
                for (Usuario usuario : usuarios) {
                    if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContraseñaUsuario().equals(contraseñaUsuario)) {
                        // El usuario y la contraseña coinciden, se encontró el usuario
                        usuarioEncontrado = true;
                        break;
                    }
                }

                callback.onResult(usuarioEncontrado);
            }

            @Override
            public void onFail(String msg) {
                callback.onResult(false);
            }
        });
    }
    public interface Callback {
        void onResult(boolean usuarioEncontrado);
    }
}

/*public class UsuarioController {


    int usuarioEncontrado = 0;

    private Context context;

    public UsuarioController(Context context) {
        this.context = context;
    }


    public int comprobarUsuario(EditText usuario, EditText contraseña) {
        String nombreUsuario = usuario.getText().toString();
        String contraseñaUsuario = contraseña.getText().toString();
        usuarioEncontrado = 0;
        Toast.makeText(context, nombreUsuario, Toast.LENGTH_LONG).show();
        Toast.makeText(context, contraseñaUsuario, Toast.LENGTH_LONG).show();
        Toast.makeText(context, usuarioEncontrado, Toast.LENGTH_LONG).show();

        UsuarioDao usuarioDao = new UsuarioDao(this.context);
        usuarioDao.obtenerUsuarios(new UsuarioDao.CallBack() {
            @Override
            public void onSuccess(List<Usuario> usuarios) {


                for (Usuario usuario : usuarios) {
                    if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContraseñaUsuario().equals(contraseñaUsuario)) {
                        // El usuario y la contraseña coinciden, se encontró el usuario
                        usuarioEncontrado = 1;
                        break;

                    }
                }

            }

            @Override
            public void onFail(String msg) {

            }

        });
       return usuarioEncontrado;
    }

}*/
