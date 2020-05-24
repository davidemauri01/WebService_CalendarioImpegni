/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.sql.*;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Mark
 */
public class DbOperations {
    private static String filterName = "";
    private static String filterDate = "";
    
    public DbOperations() {
    }
    
    public static void setNameFilter(String nome){
        filterName = nome;
    }
    
    public static void setDateFilter(String data){
        filterDate = data;
    }
    
    public static ArrayList<Impegno> getImpegno() {
        String nome = "";
        int durata = 0;
        int importanza = 0;
        String data = "";
        String materia = "";
        String tipologia = "";
        ArrayList<Impegno> res = new ArrayList<Impegno>(); // result of the query
        try {
            // create our mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            Class.forName(myDriver);
            String myUrl = "jdbc:mysql://localhost:3306/dbimpegni_tep?serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            //String query = "SELECT * FROM impegni WHERE Nome = " + nomeImpegno + "";
            // create the java statement
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM impegni";
            if (!filterDate.equals("")) {
                query += "WHERE Data = '" + filterDate + "'";
            }
            if (!filterName.equals("")) {
                if (query.contains("WHERE")) {
                    query += "AND Nome = '" + filterName + "'";
                }
                else{
                    query += "WHERE Nome = '" + filterName + "'";
                }
            }
            // execute the query, and get a java resultset
            ResultSet rs = stmt.executeQuery(query);
            // iterate through the java resultset
            while (rs.next()) {
                nome = rs.getString("Nome");
                data = rs.getString("Data");
                durata = rs.getInt("Durata");
                importanza = rs.getInt("Importanza");
                materia = rs.getString("Materia");
                tipologia = rs.getString("Tipologia");
                Impegno tempImpegno = new Impegno(nome, data, materia, tipologia, durata, importanza);
                res.add(tempImpegno);
            }
            stmt.close();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
        return res;
    }

    /**
     * @brief print a list of appointments
     * @param listaImpegni
     */
    public static String printImpegni(ArrayList<Impegno> listaImpegni) {
        String res = "";
        res += "<numeroImpegni>" + listaImpegni.size() + "</numeroImpegni>";
        for (Impegno i : listaImpegni) { // build the string
            res += "<impegno>" + i.myToString() + "</impegno>";
        }
        return res;
    }

    /**
     * @brief add an user
     */
    public static void loginUtente(String username, String password) throws SQLException, ClassNotFoundException {
        // create our mysql database connection
        String myDriver = "com.mysql.jdbc.Driver";
        Class.forName(myDriver);
        String myUrl = "jdbc:mysql://localhost:3306/dbimpegni_tep?serverTimezone=UTC";
        Connection conn = DriverManager.getConnection(myUrl, "root", "");
        
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM utenti WHERE Password = '" + password + "' AND Username = '" + username + "'";
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.first()) {  //if there are no rows (results)
            throw new WebApplicationException("Wrong parameters", 406);
        }
    }
}
