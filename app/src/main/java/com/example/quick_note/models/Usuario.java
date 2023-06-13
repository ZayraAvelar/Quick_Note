package com.example.quick_note.models;

public class Usuario {

    //Variable que guarda el id de usuario
    private int idUsuario;


    //guarda el nombre del usuario
    private String nombreUsuario;


    //guarda la contraseña del usuario
    private String contraseñaUsuario;


    //constructor con parametros
    public Usuario(int idUsuario, String nombreUsuario, String contraseñaUsuario){
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
    }


    //constructor vacio
    public Usuario(){}


    /**
     * Metodo que retorna el id de usuario
     * @return
     */
    public int getIdUsuario(){
        return idUsuario;
    }


    /**
     * Metodo que cambia el id de usuario
     * @param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


    /**
     * Metodo que retorna el nombre de usuario
     * @return
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }


    /**
     * Metodo que cambia el nombre de usuario
     * @param nombreUsuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    /**
     * Metodo que retorna la contraseña de usuario
     * @return
     */
    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }


    /**
     * Metodo que cambia la contraseña de usuario
     * @param contraseñaUsuario
     */
    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }
}
