package com.example.quick_note.models;

import org.jetbrains.annotations.NotNull;

public class Nota {

    private int idNota;
    private String nombreNota;
    private String fechaNota;
    private int catNota;
    private String descNota;

    //constructor vacio
    public Nota(){

    }

    //constructor
    public Nota(@NotNull int idNota,  String nombreNota, String fechaNota, String descNota, int catNota
                  ){

        this.idNota = idNota;
        this.nombreNota = nombreNota;
        this.fechaNota = fechaNota;
        this.catNota = catNota;
        this.descNota = descNota;

    }

    /**
     * Metodo para obtener el id se nota
     * @return
     */
    public int getIdNota(){
        return idNota;
    }


    /**
     * Metodo para cambiar el numero de id
     * @param idNota
     */
    public void setIdNota(int idNota){
        this.idNota = idNota;
    }


    /**
     * Metodo para obtener el nombre ded la nota
     * @return
     */
    public String getNombreNota(){
        return nombreNota;
    }


    /**
     * Metodo para cambiar el nombre de la nota
     * @param nombreNota
     */
    public void setNombreNota(String nombreNota){
        this.nombreNota = nombreNota;
    }


    /**
     * Metodo para obtener la fecha de la nota
     * @return
     */
    public String getFechaNota(){
        return fechaNota;
    }


    /**
     * Metodo para cambiar la fecha de la nota
     * @param fechaNota
     */
    public void setFechaNota(String fechaNota){

        this.fechaNota = fechaNota;
    }


    /**
     * Metodo para obtener la categoria de la nota
     * @return
     */
    public int getCatNota(){
        return catNota;
    }


    /**Metodo para cambiar la categoria de la nota
     *
     * @param catNota
     */
    public void setCatNota(int catNota){
        this.catNota = catNota;
    }


    /**
     * Metodo para obtener la descripcion de la nota
     * @return
     */
    public String getDescNota(){

        return descNota;
    }


    /**
     * Metodo para cambiar la descripcion de la nota
     */
    public void setDescNota(String descNota){
        this.descNota = descNota;
    }

}
