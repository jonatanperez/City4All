package com.example.jonatanperez.c4a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends Activity {

    private Button btnIntroducir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        btnIntroducir = (Button)findViewById(R.id.BtnIntroducirExperiencia);

        btnIntroducir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent = new Intent(MenuPrincipal.this, PantallaCuando.class);
                //TODO
                //Creamos la información a pasar entre actividades
                 /*
                 Bundle b = new Bundle();
                 b.putString("NOMBRE", txtNombre.getText().toString());

                 //Añadimos la información al intent
                 intent.putExtras(b);
                 */

                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
