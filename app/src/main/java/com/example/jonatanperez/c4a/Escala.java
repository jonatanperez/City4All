package com.example.jonatanperez.c4a;


import android.app.ActionBar;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class Escala extends android.support.v4.app.Fragment {

    private int idPregunta;
    private TextView textView;
    private RadioGroup radioGroup;

    public Escala() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_escala, container, false);


        return v;
    }

    public void cargarDatos(int pId, String pTexto, ArrayList<Respuesta> pArrayRespuestas, int pNumRespuestas) {
        idPregunta = pId;
        textView.setText(pTexto);

        Iterator<Respuesta> it = pArrayRespuestas.iterator();
        /*while(it.hasNext()) {
            Respuesta r = it.next();
            RadioButton rb = new RadioButton(radioGroup.getContext());
            rb.setId(r.getIdRespuesta());
            //rb.setText(r.getEnunciado());
        }*/
        for(int i = 0; i < pNumRespuestas; i++) {
            RadioButton rb = new RadioButton(radioGroup.getContext());
            switch (i) {
                case 0:
                    rb.setText(it.next().getEnunciado());
                    rb.setGravity(Gravity.RIGHT);

                    break;

                case 4:
                    rb.setText(it.next().getEnunciado());
                    rb.setGravity(Gravity.LEFT);
                    break;

                default:
                    break;
            }
        }
    }


}
