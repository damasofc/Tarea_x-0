package com.example.jhair.proyecto.Usuarios;

import java.util.ArrayList;

/**
 * Created by Jhair on 28/05/2017.
 */

public class Validaciones {
    public static ArrayList<Usuario> usuarioss =new ArrayList<>();
    public static UsuarioNormal usunormal[];
    private static int contador;

    public static void inicializarusuariosnomales(){
        usunormal = new UsuarioNormal[100];

        for (int i=0;i<100;i++){
            usunormal[i]= new UsuarioNormal("","","",0);

        }
        contador=0;
    }

    public static  void crearusuarionormal(String usuario,String contra){
        usunormal[contador].setUsuario(usuario);
        usunormal[contador].setContra(contra);

        contador++;

    }

    public static boolean comprobarusuarionormal(String usuario){
        boolean k=false;

        for (int x=0;x<100;x++) {
            String a= usunormal[x].getUsuario();
            if (a.equals(usuario)){
                return true;
            }
        }
        return k;
    }

    public static int comprobarpocicionusuarioinicio(String usuario){


        for (int x=0;x<100;x++) {
            String a= usunormal[x].getUsuario();
            if (a.equals(usuario)){
                return x;
            }
        }
        return -1;
    }

    public static boolean comprobarusuarionormalinicio(String usuario,String contra){
        boolean k=false;

        for (int x=0;x<100;x++) {
            String a= usunormal[x].getUsuario();
            String b= usunormal[x].getContra();
            if (a.equals(usuario)&&b.equals(contra)){
                return true;
            }
        }
        return k;
    }


















}
