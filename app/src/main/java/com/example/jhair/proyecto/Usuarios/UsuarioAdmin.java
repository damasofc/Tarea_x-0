package com.example.jhair.proyecto.Usuarios;

import java.util.ArrayList;

/**
 * Created by Jhair on 28/05/2017.
 */

public class UsuarioAdmin extends Usuario {

    private ArrayList<String> eventosadmin;


    public UsuarioAdmin(String usuario, String contra, String nomcompleto, int edad) {
        super(usuario, contra, nomcompleto, edad);
        eventosadmin = new ArrayList<>();



    }
}
