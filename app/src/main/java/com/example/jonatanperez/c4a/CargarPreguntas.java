package com.example.jonatanperez.c4a;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jonatanperez on 6/5/15.
 */
public class CargarPreguntas extends AsyncTask<String, String, String> {

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> listaPreguntas = new ArrayList<HashMap<String, String>>();

    // url to get all products list


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PID = "pid";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_NAME = "name";

    // products JSONArray
    JSONArray preguntas = new JSONArray();

    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * getting All products from url
     * */
    protected String doInBackground(String... args) {
        // Building Parameters
        ArrayList<Pregunta> params = new ArrayList<Pregunta>();
        // getting JSON string from URL
        String cargarTextos = "http://10.106.25.40/City4All/android_connect/prueba3.php";
        JSONObject json = jParser.makeHttpRequest(cargarTextos, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All Products: ", json.toString());

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                preguntas = json.getJSONArray(TAG_PRODUCTS);

                // looping through All Products
                for (int i = 0; i < preguntas.length(); i++) {
                    JSONObject c = preguntas.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString(TAG_PID);
                    String name = c.getString(TAG_NAME);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_PID, id);
                    map.put(TAG_NAME, name);

                    // adding HashList to ArrayList
                    listaPreguntas.add(map);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * After completing background task Dismiss the progress dialog
     * **/
    protected void onPostExecute(String file_url) {
        // dismiss the dialog after getting all products

        // updating UI from Background Thread
    }
}
