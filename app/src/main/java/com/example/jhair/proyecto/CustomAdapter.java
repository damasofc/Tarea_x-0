package com.example.jhair.proyecto;

/**
 * Created by Julio on 19/06/2017.
 */
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhair.proyecto.clases.Evento;
import com.example.jhair.proyecto.clases.EventoDeportivo;
import com.example.jhair.proyecto.clases.EventoMusical;
import com.example.jhair.proyecto.clases.EventoReligioso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Evento> {
    public CustomAdapter(@NonNull Context context, ArrayList<Evento>eventos) {
        super(context, R.layout.custom_listview ,eventos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_listview, parent, false);

        Evento singleItem = getItem(position);
        //aqui voy a inicializar cada uno de los elementos de mi custom list view
        TextView TextViewCodigo = (TextView) customView.findViewById(R.id.textViewCodigo);
        TextView TextViewTipo = (TextView) customView.findViewById(R.id.textViewTipo);
        TextView TextViewTitulo = (TextView) customView.findViewById(R.id.textViewTitulo);
        TextView TextViewFecha = (TextView) customView.findViewById(R.id.textViewFecha);
        TextView TextViewMonto = (TextView) customView.findViewById(R.id.textViewMonto);
        ImageView ImagenLista = (ImageView) customView.findViewById(R.id.imageViewLista);
        try {
            TextViewCodigo.setText("Codigo: "+String.valueOf(singleItem.getCodigo()));
            //para saber el tipo de evento
            if(singleItem instanceof EventoDeportivo) {
                TextViewTipo.setText("Tipo de evento: Deportivo");
            }
            else if(singleItem instanceof EventoMusical) {
                TextViewTipo.setText("Tipo de evento: Musical");
            }
            else if(singleItem instanceof EventoReligioso) {
                TextViewTipo.setText("Tipo de evento: Religioso");
            }

            TextViewTitulo.setText("Titulo: "+singleItem.getTitulo());
            TextViewFecha.setText("Fecha: "+singleItem.getFechaString());
            TextViewMonto.setText("Monto a pagar: "+String.valueOf(singleItem.getMontoPagar()));
            ImagenLista.setImageResource(R.mipmap.androidxd);

        } catch (Exception e) {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        //para saber el tipo de dato


        return customView;
    }
}
