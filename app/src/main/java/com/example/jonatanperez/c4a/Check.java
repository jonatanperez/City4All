package com.example.jonatanperez.c4a;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class Check extends android.support.v4.app.Fragment {

    private int idPregunta;
    private TextView textView;
    //private RadioGroup radioGroup;
    private ArrayList<Respuesta> respuestas;


    public Check() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_check, container, false);
        textView = (TextView) v.findViewById(R.id.textoPregunta);
        //radioGroup = (RadioGroup)v.findViewById(R.id.radioRespuestas);

        return v;
    }

    public void cargarDatos(int pId, String pTexto, ArrayList<Respuesta> pArrayRespuestas) {
        idPregunta = pId;
        textView.setText(pTexto);

        Iterator<Respuesta> it = pArrayRespuestas.iterator();
        while(it.hasNext()) {
            Respuesta r = it.next();
            //RadioButton rb = new RadioButton(radioGroup.getContext());
            CheckBox cb = new CheckBox(this.getActivity());
            cb.setId(r.getIdRespuesta());
            cb.setText(r.getEnunciado());
        }
    }



}
