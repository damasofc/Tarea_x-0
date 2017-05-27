package com.example.jhair.proyecto.clases;


import java.util.Calendar;
import java.util.Locale;

public class Evento {
    protected int codigo;
    protected String descripcion;
    protected String titulo;
    protected Calendar fecha;//la fecha en la que se relizara el evento
    protected double montoPagar;//este es el monto a pagar
    Locale locale = Locale.getDefault();

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Evento(int codigo, String titulo, Calendar fecha, double montoPagar, String descripcion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.fecha = fecha;
        this.montoPagar = montoPagar;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public double getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(double montoPagar) {
        this.montoPagar = montoPagar;
    }

    public String getFechaString(){
        String dia = fecha.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,locale);
        String mes = fecha.getDisplayName(Calendar.MONTH,Calendar.LONG,locale);
        return dia+", "+fecha.get(Calendar.DAY_OF_MONTH)+" de "+ mes+", "+fecha.get(Calendar.YEAR);
    }
}
