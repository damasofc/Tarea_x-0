package com.example.jhair.proyecto.clases;

import java.util.Calendar;

public class EventoReligioso extends Evento {
    public static final int CANTIDADMAXIMA = 30000;
    public static final int seguroFijo = 2000;
    private int personasConvertidas;
    public EventoReligioso(int codigo, String titulo, Calendar fecha, double montoPagar) {
        super(codigo, titulo, fecha, montoPagar);
        montoPagar += seguroFijo;
        personasConvertidas = 0;
    }

    public int getPersonasConvertidas() {
        return personasConvertidas;
    }

    public void setPersonasConvertidas(int personasConvertidas) {
        this.personasConvertidas = personasConvertidas;
    }
}
