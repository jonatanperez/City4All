package com.example.jonatanperez.c4a;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class Radio extends android.support.v4.app.Fragment {

    private int idPregunta;
    private TextView textView;
    private RadioGroup radioGroup;


    public Radio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio, container, false);
    }

    public void cargarDatos(int pId, String pTexto, ArrayList<Respuesta> pArrayRespuestas) {
        idPregunta = pId;
        textView.setText(pTexto);

        Iterator<Respuesta> it = pArrayRespuestas.iterator();
        while(it.hasNext()) {
            Respuesta r = it.next();
            RadioButton rb = new RadioButton(radioGroup.getContext());
            rb.setId(r.getIdRespuesta());
            rb.setText(r.getEnunciado());
        }
    }


}
