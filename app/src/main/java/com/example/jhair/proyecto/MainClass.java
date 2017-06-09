package com.example.jhair.proyecto;

import com.example.jhair.proyecto.clases.Evento;

import java.util.ArrayList;

public class MainClass {
    public static ArrayList<Evento> eventos = new ArrayList<>();
    public static ArrayList<Evento> eventosCancelados = new ArrayList<>();

    public static boolean añadirEvento(Evento e) {
        if (existeEvento(e.getCodigo())) {
            return false;
        } else {
            eventos.add(e);
            return true;
        }
    }

    //este metodo es para comprobar si existe ese evento o no
    public static boolean existeEvento(int codigo) {
        for (Evento i : eventos) {
            if (i.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public static void borrarEvento(int cod) {
        for (Evento e : eventos) {
            if (e.getCodigo() == cod) {
                eventos.remove(e);
            }
        }
    }

    public static Evento buscarEvento(int cod) {
        for (Evento e : eventos) {
            if (e.getCodigo() == cod) {
                return e;
            }
        }
        return null;
    }
    public static Evento buscarEventoCancelado(int cod) {
        for (Evento e : eventosCancelados) {
            if (e.getCodigo() == cod) {
                return e;
            }
        }
        return null;
    }

    public static String formatMontoPago(double i){
        String monto = String.valueOf(i);
        int val = 0;
        for (int m = monto.length()-1; m >= 0;m--){
            if(monto.charAt(m) == '.'){
                val = m;
            }
        }
        String mon = monto.substring(0,val);
        int contador = 1;
        for (int s = mon.length()-1; s > 0;s--){
            if(contador % 3 == 0){
                mon = mon.substring(0,s)+","+mon.substring(s,mon.length());
            }
            contador++;
        }
        return mon + monto.substring(val);
    }
}
