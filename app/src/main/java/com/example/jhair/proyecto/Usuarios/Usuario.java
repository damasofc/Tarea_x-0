package com.example.jhair.proyecto.Usuarios;

/**
 * Created by Jhair on 28/05/2017.
 */

public class Usuario {
    protected String usuario;
    protected String contra;
    protected String nomcompleto;
    protected int edad;

    public Usuario(String usuario, String contra, String nomcompleto, int edad) {
        this.usuario = usuario;
        this.contra = contra;
        this.nomcompleto = nomcompleto;
        this.edad = edad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNomcompleto() {
        return nomcompleto;
    }

    public void setNomcompleto(String nomcompleto) {
        this.nomcompleto = nomcompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
