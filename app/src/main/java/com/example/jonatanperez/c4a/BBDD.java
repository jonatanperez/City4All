package com.example.jonatanperez.c4a;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;


/**
 * Created by jonatanperez on 6/5/15.
 */
public class BBDD {

    private static BBDD bd;

    // Hashmap for ListView
    private ArrayList<HashMap<String, String>> productsList = null;
    private Context ctx = null;

    private BBDD() {
        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();
    }

    public static BBDD getBBDD() {
        if(bd == null) {
            bd = new BBDD();
        }
        return bd;
    }

    public void setContext(Context pCtx) {
        ctx = pCtx;
    }

    public void dd() {
        new CargarPreguntas().execute();
    }


}
