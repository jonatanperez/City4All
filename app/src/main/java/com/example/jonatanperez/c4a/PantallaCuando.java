package com.example.jonatanperez.c4a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Button;

public class PantallaCuando extends Activity {
    private RadioGroup rdgCuando;
    private Button btnAceptar;
    private boolean cuando = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cuando);

        rdgCuando = (RadioGroup)findViewById(R.id.RgrpCuando);
        btnAceptar = (Button)findViewById(R.id.BtnAceptar);

        rdgCuando.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.RbAhora){
                    cuando = true;
                } else {
                    cuando = false;
                }
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent;
                if(cuando == true) {
                    intent = new Intent(PantallaCuando.this, PreguntaEscala.class);
                } else {
                    //Llamar a la pantalla del mapa
                    intent = new Intent(PantallaCuando.this, PreguntaCheck.class);
                }
                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pantalla_cuando, menu);
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
