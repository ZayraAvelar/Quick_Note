package com.example.quick_note.models;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quick_note.GlobalVariables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    private List<Categoria> categorias;
    private Context context;
    private RequestQueue mRequestQueue;

    public CategoriaDao(Context context) {
        this.context = context;
        categorias = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this.context);
    }


    //sube la categoria a la base de datos
    public CategoriaDao subirCategoria(final String nombre) {
        String url = GlobalVariables.URL+"registrarCat2/" +  nombre;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud
                    }
                });
        // Agregar la solicitud a la cola de Volley
        Volley.newRequestQueue(context).add(stringRequest);

        return null;
    }

    //obtiene las categorias
    public CategoriaDao obtenerCategorias(final CategoriaDao.CallBack onCallBack){
        //URL del endpoint
        String url = GlobalVariables.URL+"categorias";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Categoria categoria = new Categoria();
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    Log.d("JSON Response", response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int idCategoria = jsonObject.getInt("catId");
                        String nombreCat = jsonObject.getString("catNombre");


                        categorias.add(new Categoria(idCategoria,nombreCat));

                    }//fin del for
                    onCallBack.onSuccess(categorias);
                } catch (JSONException e) {
                    e.printStackTrace();
                    onCallBack.onFail(e.toString());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(stringRequest);
        return null;
    }

    public interface CallBack {
        void onSuccess(List<Categoria> categorias);
        void onFail(String msg);
    }
}
