package com.example.jonatanperez.c4a;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jonatanperez on 16/4/15.
 */
public class ListaPreguntas {
    private static ArrayList<Pregunta> listaPreguntas;

    private ListaPreguntas() {

    }

    public static ArrayList<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    /*public Pregunta buscarPreguntaporId(int idPregunta) {
        Iterator<Pregunta> itr = new Iterator<Pregunta>();
        int i = 0;
        while(itr.hasNext()) {
            if(ListaPreguntas.getListaPreguntas().get(i).getIdPregunta() == idPregunta) {
                return ListaPreguntas.getListaPreguntas().get(i);
            }
        }
    }*/
}
