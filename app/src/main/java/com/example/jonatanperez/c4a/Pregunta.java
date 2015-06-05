package com.example.jonatanperez.c4a;

import java.util.ArrayList;

/**
 * Created by jonatanperez on 18/3/15.
 */
public class Pregunta {
    private int idPregunta;
    private int numPregunta;
    private String enunciado;
    private String tipoRespuesta;
    private int numRespuestas;
    private ArrayList<Respuesta> respuestas;

    public Pregunta(int pIdPregunta, int pNumPregunta, String pEnunciado, String pTipoRespuesta, int pNumRespuestas) {
        this.idPregunta = pIdPregunta;
        this.numPregunta = pNumPregunta;
        this.enunciado = pEnunciado;
        this.tipoRespuesta = pTipoRespuesta;
        this.numRespuestas = pNumRespuestas;
        this.respuestas = new ArrayList<Respuesta>();
    }

    public int getIdPregunta() {
        return this.idPregunta;
    }

    public int getNumPregunta() {
        return this.numPregunta;
    }

    public ArrayList<Respuesta> getListaRespuestas() {
        return this.respuestas;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getTipoRespuesta() {
        return tipoRespuesta;
    }

    public int getNumRespuestas() {
        return numRespuestas;
    }
}
