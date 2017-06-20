package com.example.jhair.proyecto.Usuarios;

import com.example.jhair.proyecto.clases.Evento;

import java.util.ArrayList;

/**
 * Created by Jhair on 28/05/2017.
 */

public class UsuarioNormal extends Usuario {

    public static ArrayList<Evento> eventosnormales;


    public UsuarioNormal(String usuario, String contra, String nomcompleto, int edad) {
        super(usuario, contra, nomcompleto, edad);

    }

    public static void agregarEventoNormal(Evento e){
        eventosnormales.add(e);
    }






}
