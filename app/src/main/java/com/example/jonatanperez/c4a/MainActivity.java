package com.example.jonatanperez.c4a;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.Calendar;


public class MainActivity extends Activity {

    private EditText txtAnoNcto;
    private EditText txtResidencia;
    private RadioGroup rgrpSexo;
    private Button btnAceptar;
    private int sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkDB();
        //Obtenemos una referencia a los controles de la interfaz
        txtResidencia = (EditText) findViewById(R.id.TxtCiudad);
        rgrpSexo = (RadioGroup)findViewById(R.id.RgrpSexo);
        rgrpSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.RbMujer){
                    sexo = 1;
                } else {
                    if(checkedId == R.id.RbHombre) {
                        sexo = 2;
                    } else {
                        sexo = 0;
                    }
                }
            }
        });
        btnAceptar = (Button)findViewById(R.id.BtnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Realizamos las validaciones
                JSONObject json = new JSONObject();
                if(validarSexo(v) && validarNacimiento(v) && validarResidencia(v)) {
                    //Si son correctas, guardamos los datos del usuario


                    try{
                        if(sexo == 1) {
                            json.put("SEXO", "Mujer");
                        } else {
                            if(sexo == 2) {
                                json.put("SEXO", "Hombre");
                            }
                        }
                        json.put("AÑONCTO", txtAnoNcto.getText());
                        json.put("RESIDENCIA", txtResidencia.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    FileOutputStream fos;
                    try {
                        fos = getApplicationContext().openFileOutput("usuario.json", Context.MODE_PRIVATE);
                        fos.write(json.toString().getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Creamos el Intent
                    Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                    //TODO
                    //Creamos la información a pasar entre actividades
                    /*Bundle b = new Bundle();
                    b.putString("NOMBRE", txtNombre.getText().toString());

                    //Añadimos la información al intent
                    intent.putExtras(b);*/

                    //Iniciamos la nueva actividad
                    startActivity(intent);
                } else {
                    //Si falla alguna de las validaciones, mostramos un mensaje de error
                    TextView lblError = (TextView)findViewById(R.id.LblError);
                    lblError.setTextColor(Color.RED);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onRadioButtonClicked(View view) {

    }

    public boolean validarSexo(View view) {
        boolean valido = true;

        if(sexo == 0) {
            valido = false;
        } else {
            valido = true;
        }
        return valido;
    }

    public boolean validarResidencia(View view) {
        boolean valido = true;

        int i = 0;

        txtResidencia = (EditText) findViewById(R.id.TxtCiudad);
        if(txtResidencia.getText().length() == 0) {
            valido = false;
        } else {
            while (valido && i < txtResidencia.getText().length()) {
                char c = txtResidencia.getText().charAt(i);
                if (!Character.isLetter(c) && c!=' ') {
                    valido = false;
                } else {
                    i = i + 1;
                }
            }
        }
        return valido;
    }

    public boolean validarNacimiento(View view) {
        boolean valido = true;

        int i = 0;

        txtAnoNcto = (EditText) findViewById(R.id.TxtAnoNcto);
        if(txtAnoNcto.getText().length() == 0) {
            valido = false;
        } else {
            while (valido && i < txtAnoNcto.getText().length()) {
                char c = txtAnoNcto.getText().charAt(i);
                if (!Character.isDigit(c)) {
                    valido = false;
                } else {
                    i = i + 1;
                }
            }
        }
        return valido;
    }

    private void checkDB() {
        GestorBD gbd = GestorBD.getInstance();
        if(gbd != null) {
            Toast.makeText(this, "Todo ok", Toast.LENGTH_SHORT).show();
        }
    }

/*
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    */
}