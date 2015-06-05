package com.example.jonatanperez.c4a;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GestorBD {

    private String usuario;
    private String clave;
    private String url;
    private String driverClassName;
    private Connection conn = null;
    private Statement sentencia;
    private static GestorBD miGestor;
    private Context context; //para el toast ok TODO

    /** Constructor	**/
    private GestorBD() {

        this.usuario = "root";
        this.clave = "";
        this.url = "jdbc:mysql://127.0.0.1:3306";
        this.driverClassName = "com.mysql.jdbc.Driver";

        try {
            abrirConexion();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public static GestorBD getInstance() {
        if(miGestor == null) {
            miGestor = new GestorBD();
        }
        return miGestor;
    }


    private void abrirConexion() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(this.url, this.usuario, this.clave);
            sentencia = this.conn.createStatement();
            Toast.makeText(context, "Todo OK", Toast.LENGTH_LONG).show();
        } catch (NullPointerException err) {
            Log.e("ERROR BD", "HE FALLADO");
        } catch (ClassNotFoundException e2) {
            Log.e("ERROR CLASE", "HE FALLADO TAMBIEN");
        }
    }


    private void comprobarConexion() throws SQLException{

        if (conn == null || conn.isClosed())
            abrirConexion();
    }


    public ResultSet consulta(String strSQL) throws SQLException {
        comprobarConexion();
        ResultSet rs = sentencia.executeQuery(strSQL);
        return rs;
    }


    public int actualizar(String strSQL) throws SQLException {
        comprobarConexion();
        return sentencia.executeUpdate(strSQL);
    }


    public int borrar(String strSQL) throws SQLException {
        comprobarConexion();
        return sentencia.executeUpdate(strSQL);
    }


    public int insertar(String strSQL) throws SQLException {
        comprobarConexion();
        return sentencia.executeUpdate(strSQL);
    }

    public void agregarSentencia(String strSQL) throws SQLException {
        sentencia.addBatch(strSQL);
    }

    public void ejecutarSentencias() throws SQLException{
        sentencia.executeBatch();
        sentencia.clearBatch();
    }

    public int cargarEncuestaActiva() {
        int rdo = -1;
        ResultSet rs = null;

        String strSQL = "SELECT version FROM Encuesta WHERE activa='1';";

        try {
            rs = GestorBD.miGestor.consulta(strSQL);
            if (rs.first()) {
                rdo = rs.getInt("version");
            }
        } catch(SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
        }
        return rdo;
    }

    private ArrayList<Pregunta> cargarPreguntas(int version, ArrayList<Pregunta> listaPreguntas) {
        //Obtenemos la version activa
        String strSQL = "SELECT COUNT(DISTINCT num_pregunta) totalPreguntas FROM EncuestaPreguntaRespuesta WHERE version = " + version;

        try {
            ResultSet rs = GestorBD.miGestor.consulta(strSQL);
            rs.first();
            do {
                int totalPreguntas = rs.getInt("totalPreguntas");

                //Obtenemos los id's de las preguntas que forman la encuesta
                strSQL = "SELECT DISTINCT idPregunta, num_pregunta FROM EncuestaPreguntaRespuesta WHERE version = " + version + " ORDER BY num_pregunta";

                    ResultSet rs2 = GestorBD.miGestor.consulta(strSQL);
                    rs2.first();
                    do {
                        int idPregunta = rs2.getInt("idPregunta");
                        int numPregunta = rs2.getInt("num_pregunta");

                        //Obtenemos los textos de las preguntas
                        strSQL = "SELECT texto FROM Texto WHERE idPregunta = " + idPregunta;

                            ResultSet rs3 = GestorBD.miGestor.consulta(strSQL);
                            rs3.first();
                            do {
                                String enunciado = rs3.getString("texto");

                                //Obtenemos el tipo de respuesta y el número de posibles respuestas
                                strSQL = "SELECT tipo_respuesta, num_respuestas FROM Pregunta WHERE idPregunta = " + idPregunta;

                                    ResultSet rs4 = GestorBD.miGestor.consulta(strSQL);
                                    rs4.first();
                                    do {
                                        String tipoRespuesta = rs4.getString("tipo_respuesta");
                                        int numRespuestas = rs4.getInt("num_respuestas");
                                        Pregunta pregunta = new Pregunta(idPregunta, numPregunta, enunciado, tipoRespuesta, numRespuestas);
                                        listaPreguntas.add(pregunta);
                                    } while (rs4.next());
                            } while (rs3.next());

                    } while (rs2.next());
            } while (rs.next());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return listaPreguntas;
    }

    public Iterator<Pregunta> cargarRespuestas(int version) {
        ArrayList<Pregunta> listaPreguntas = new ArrayList<>();

        this.cargarPreguntas(version, listaPreguntas);

        for(int i = 0; i < listaPreguntas.size(); i++) {
            String strSQL = "SELECT idRespuesta FROM EncuestaPreguntaRespuesta WHERE version = " + version + "AND idPregunta = " + listaPreguntas.get(i).getIdPregunta();

            try {
                ResultSet rs = GestorBD.miGestor.consulta(strSQL);
                rs.first();
                do {
                    int idRespuesta = rs.getInt("idRespuesta");

                    //Obtenemos los textos de las respuestas
                    strSQL = "SELECT texto FROM Texto WHERE idRespuesta = " + idRespuesta;

                        ResultSet rs2 = GestorBD.miGestor.consulta(strSQL);
                        rs2.first();
                        do {
                            String enunciado = rs2.getString("texto");
                            Respuesta respuesta = new Respuesta(idRespuesta, enunciado);
                            listaPreguntas.get(i).getListaRespuestas().add(respuesta);
                        } while (rs2.next());

                } while (rs.next());
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return listaPreguntas.iterator();


        //OTRA FORMA

        /*String strSQL = "SELECT idPregunta, idRespuesta FROM EncuestaPreguntaRespuesta WHERE version = " + version /* + "AND idPregunta = " + listaPreguntas.get(i).getIdPregunta();

        try {
            ResultSet rs = GestorBD.miGestor.consulta(strSQL);
            rs.first();
            do {
                int idPregunta = rs.getInt("idPregunta");
                int idRespuesta = rs.getInt("idRespuesta");

                //Obtenemos los textos de las respuestas
                strSQL = "SELECT texto FROM Texto WHERE idRespuesta = " + idRespuesta;

                try {
                    ResultSet rs2 = GestorBD.miGestor.consulta(strSQL);
                    rs2.first();
                    do {
                        String enunciado = rs2.getString("texto");
                        Respuesta respuesta = new Respuesta(idRespuesta, enunciado);
                        //Pregunta pregunta = listaPreguntas.buscarPreguntaporId(idPregunta);
                        //pregunta.getListaRespuestas().add(respuesta);
                    } while (rs2.next());
                } catch (SQLException ex) {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
            } while (rs.next());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }*/
    }
}