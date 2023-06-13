package com.example.quick_note.models;

import org.jetbrains.annotations.NotNull;

public class Categoria {

    private int idCategoria;

    private String nombreCategoria;

    //constructor vacio
    public Categoria(){}


   //constructor con parametros
    public Categoria(@NotNull int idCategoria, String nombreCategoria){
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }


    /**
     * Retorna el id de la categoria
     * @return
     */
    public int getIdCategoria(){
        return idCategoria;
    }


    /**
     * Cambia el id de la categoria
     * @param idCategoria
     */
    public void setIdCategoria(int idCategoria){
        this.idCategoria = idCategoria;
    }


    /**
     * Retorna el nombre de la categoria
     * @return
     */
    public String getNombreCategoria(){
        return nombreCategoria;
    }


    /**
     * Cambia el nombre de la categoria
     * @param nombreCategoria
     */
    public void setNombreCategoria(String nombreCategoria){
        this.nombreCategoria = nombreCategoria;
    }
}
