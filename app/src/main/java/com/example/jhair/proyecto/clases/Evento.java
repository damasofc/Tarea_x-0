package com.example.jhair.proyecto.clases;


import java.util.Calendar;

public class Evento {
    protected int codigo;
    protected String titulo;
    protected Calendar fecha;//la fecha en la que se relizara el evento
    protected double montoPagar;//este es el monto a pagar

    public Evento(int codigo, String titulo, Calendar fecha, double montoPagar) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.fecha = fecha;
        this.montoPagar = montoPagar;
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
}
