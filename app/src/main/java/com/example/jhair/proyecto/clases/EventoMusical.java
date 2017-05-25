package com.example.jhair.proyecto.clases;

import java.util.ArrayList;
import java.util.Calendar;

public class EventoMusical extends Evento {
    public enum Musica{
        POP,ROCK,RAP,CLASICA,REGGAETON,OTRO;
    }
    ArrayList<String> staff;
    public static final double seguroGrama = 0.3;
    private Musica tipoMusica;
    public static final int CANTIDADMAXIMA = 25000;
    public EventoMusical(int codigo, String titulo, Calendar fecha, double montoPagar ,String musica) {
        super(codigo, titulo, fecha, montoPagar);
        montoPagar = montoPagar + (montoPagar*seguroGrama);
        tipoMusica = Musica.valueOf(musica);
        staff = new ArrayList<>();
    }
}
