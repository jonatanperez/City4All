package com.example.jonatanperez.c4a;

import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jonatanperez on 17/4/15.
 */
public class Adaptador extends FragmentPagerAdapter {

    private ArrayList<Fragment> paginas = new ArrayList();

    public Adaptador(FragmentManager fm) {
        super(fm);
        //Iterator<Pregunta> preguntas = GestorBD.getInstance().cargarRespuestas(GestorBD.getInstance().cargarEncuestaActiva());
        CargarPreguntas cp = new CargarPreguntas();
        String jsonString = cp.doInBackground(null);

        int i = 0;

        /*
        while (preguntas.hasNext()) {
            Pregunta p = preguntas.next();
            if(p.getTipoRespuesta().equals("ESCALA")) {
                Escala escala = new Escala();
                escala.cargarDatos(p.getIdPregunta(), p.getEnunciado(), p.getListaRespuestas(), p.getNumRespuestas());
                /**
                 * TODO
                 * Añadir métodos para rellenar datos.
                 *
                paginas.add(escala);
            } else if(p.getTipoRespuesta().equals("CHECK")) {
                Check check = new Check();
                paginas.add(check);
            } else if(p.getTipoRespuesta().equals("RADIO")) {
                Radio radio = new Radio();
                radio.cargarDatos(p.getIdPregunta(), p.getEnunciado(), p.getListaRespuestas());
                paginas.add(radio);
            } else {
                throw new NullPointerException();
            }
        }*/

    }

    @Override
    public Fragment getItem(int position) {
        return paginas.get(position);
    }

    @Override
    public int getCount() {
        return paginas.size();
    }
}
