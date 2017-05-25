package com.example.jhair.proyecto;

import com.example.jhair.proyecto.clases.Evento;

import java.util.ArrayList;

public class MainClass {
    public static ArrayList<Evento> eventos = new ArrayList<>();
    public static boolean añadirEvento( Evento e){
        if(existeEvento(e.getCodigo())){
            return false;
        }
        else{
            eventos.add(e);
            return true;
        }
    }
    //este metodo es para comprobar si existe ese evento o no
    public static boolean existeEvento(int codigo){
        for( Evento i: eventos){
            if(i.getCodigo() == codigo){
                return true;
            }
        }
        return false;
    }
}