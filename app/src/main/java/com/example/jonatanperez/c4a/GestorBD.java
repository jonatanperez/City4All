package com.example.jonatanperez.c4a;

import java.sql.*;

public class GestorBD {

    private String usuario;
    private String clave;
    private String url;
    private String driverClassName;
    private Connection conn = null;
    private Statement sentencia;
    private static GestorBD miGestor;

    /** Constructor	**/
    private GestorBD() {

        this.usuario = "root";
        this.clave = "";
        this.url = "jdbc:mysql://10.106.30.126:3306";
        this.driverClassName = "com.mysql.jdbc.Driver";

        try {
            AbrirConexion();
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


    private void AbrirConexion() throws SQLException {

        try {
            Class.forName(this.driverClassName).newInstance();
            this.conn = DriverManager.getConnection(this.url, this.usuario, this.clave);
            sentencia = this.conn.createStatement();

        } catch (Exception err) {
            System.out.println("Error " + err.getMessage());
        }
    }


    private void comprobarConexion() throws SQLException{

        if (conn == null || conn.isClosed())
            AbrirConexion();
    }


    public ResultSet consulta(String strSQL) throws SQLException {
        comprobarConexion();
        return this.sentencia.executeQuery(strSQL);
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


    public void agregarSentencia(String strSQL) throws SQLException{
        sentencia.addBatch(strSQL);
    }


    public void ejecutarSentencias() throws SQLException{
        sentencia.executeBatch();
        sentencia.clearBatch();
    }
}
