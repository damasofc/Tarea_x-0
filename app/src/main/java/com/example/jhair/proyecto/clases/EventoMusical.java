package com.example.jhair.proyecto.clases;

import java.util.ArrayList;
import java.util.Calendar;

public class EventoMusical extends Evento {
    public enum Musica{
        POP,ROCK,RAP,CLASICA,REGGAETON,OTRO;
    }
    ArrayList<String> staff;
    private  double seguroGrama = 0.3;
    private Musica tipoMusica;

    public Musica getTipoMusica() {
        return tipoMusica;
    }

    public void setTipoMusica(Musica tipoMusica) {
        this.tipoMusica = tipoMusica;
    }

    public static final int CANTIDADMAXIMA = 25000;
    public EventoMusical(int codigo, String titulo, Calendar fecha, double montoPagar, String descripcion ,String musica) {
        super(codigo, titulo, fecha, montoPagar + (montoPagar*0.3),descripcion);
        seguroGrama = seguroGrama * montoPagar;
        tipoMusica = Musica.valueOf(musica);
        staff = new ArrayList<>();
    }

    @Override
    public void setMontoPagar(double montoPagar) {
        super.setMontoPagar(montoPagar + (montoPagar*0.3));
        seguroGrama = 0.3*montoPagar;
    }

    public double getSeguroGrama() {
        return seguroGrama;
    }

}
