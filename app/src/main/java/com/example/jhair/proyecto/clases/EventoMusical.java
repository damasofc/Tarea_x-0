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

    public Musica getTipoMusica() {
        return tipoMusica;
    }

    public void setTipoMusica(Musica tipoMusica) {
        this.tipoMusica = tipoMusica;
    }

    public static final int CANTIDADMAXIMA = 25000;
    public EventoMusical(int codigo, String titulo, Calendar fecha, double montoPagar, String descripcion ,String musica) {
        super(codigo, titulo, fecha, montoPagar + (montoPagar*seguroGrama),descripcion);
        tipoMusica = Musica.valueOf(musica);
        staff = new ArrayList<>();
    }
}
