package com.example.jhair.proyecto.Usuarios;

import java.util.ArrayList;

/**
 * Created by Jhair on 28/05/2017.
 */

public class Validaciones {
    public static ArrayList<Usuario> usuarioss =new ArrayList<>();

    //Aqui se comprueba si ya hay usuarios registrados
    public static boolean existeuser(String usuario){
        for (Usuario i: usuarioss){
            if(i.getUsuario()==usuario){
                return true;
            }
        }
        return false;
    }



}
