package com.example.jonatanperez.c4a;

import java.util.ArrayList;

/**
 * Created by jonatanperez on 18/3/15.
 */
public class Respuesta {
    private int idRespuesta;
    private String enunciado;

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public Respuesta(int pIdRespuesta, String pEnunciado) {
        this.idRespuesta = pIdRespuesta;

        this.enunciado = pEnunciado;
    }
}
