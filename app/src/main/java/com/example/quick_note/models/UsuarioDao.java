package com.example.quick_note.models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quick_note.GlobalVariables;
import com.example.quick_note.LoginActivity;
import com.example.quick_note.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private List<Usuario> usuarios;
    private Context context;
    private RequestQueue mRequestQueue;

    public UsuarioDao(Context context) {
        this.context = context;
        usuarios = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this.context);
    }

    public void obtenerUsuarios(final UsuarioDao.CallBack onCallBack) {
        // URL del endpoint
        String url = GlobalVariables.URL + "usuarios";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<Usuario> usuarios = new ArrayList<>();

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            // Recorremos el array JSON y almacenamos los datos en una lista
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                // Obtenemos los datos con base a las claves del JSON
                                int idUsuario = jsonObject.getInt("idUsuario");
                                String nombreUsuario = jsonObject.getString("nombreUsuario");
                                String contraseñaUsuario = jsonObject.getString("contraseñaUsuario");

                                // Creamos el objeto Usuario y lo agregamos a la lista
                                Usuario usuario = new Usuario(idUsuario, nombreUsuario, contraseñaUsuario);
                                usuarios.add(usuario);
                            }
                            // Llamamos al método onSuccess en el CallBack
                            onCallBack.onSuccess(usuarios);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            onCallBack.onFail(e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onCallBack.onFail(error.toString());
                    }
                });

        mRequestQueue.add(stringRequest);
    }

    public interface CallBack {
        void onSuccess(List<Usuario> usuarios);
        void onFail(String msg);
    }
}
