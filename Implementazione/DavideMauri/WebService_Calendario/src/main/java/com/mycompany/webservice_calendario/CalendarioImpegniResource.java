/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webservice_calendario;

import java.io.IOException;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * REST Web Service
 * Web Service per la gestione di un Calendario di Impegni per lo studente odierno
 * @author Davide Mauri
 */
@Path("calendarioImpegni")
public class CalendarioImpegniResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CalendarioImpegniResource
     */
    public CalendarioImpegniResource() {
    }
    
    
    /**
     * Visualizza l'intero elenco degli impegni in formato xml
     * @return ris elenco impegni in xml
     */
    @GET
    @Path("elenco")
    @Produces(MediaType.APPLICATION_XML)
    public String getElencoImpegni(){
        
        final dbManager db = dbManager.getInstance();
        
        if(!db.isConnected()){
            System.err.println("DB non connesso");
            throw new InternalServerErrorException("DB non connesso!");           
        }
        
        String sql = "SELECT * FROM impegni";
        try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
            
            StringBuilder ris;
            try (ResultSet result = statement.executeQuery()) {
                ris = new StringBuilder();
                ris.append("<impegni>");
                while (result.next()) {
                    ris.append("<impegno>");
                    ris.append("<id>").append(result.getInt(1)).append("</id>");
                    ris.append("<nome>").append(result.getString(2)).append("</nome>");
                    ris.append("<data>").append(result.getString(3)).append("</data>");
                    ris.append("<durata>").append(result.getString(4)).append("</durata>");
                    ris.append("<importanza>").append(result.getInt(5)).append("</importanza>");
                    ris.append("<materia>").append(result.getString(6)).append("</materia>");
                    ris.append("<tipologia>").append(result.getString(7)).append("</tipologia>");
                    ris.append("<idUtente>").append(result.getInt(8)).append("</idUtente>");
                    ris.append("</impegno>");
                }
                ris.append("</impegni>");
            }

            return ris.toString();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("DBMS server error!");
        }
    }

    /**
     * Registra i dati di un utente, ovvero username e password, sul DataBase, e
     * poi ritorna l'id dell'utente
     * @param xml contiene i dati dell'utente
     * @return id dell'utente appena registrato
     */
    @POST
    @Path("registraUtente")
    @Consumes({MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public String createScheda(String xml) throws ParserConfigurationException, IOException, SAXException, org.xml.sax.SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document document;
        document = builder.parse(is);
        final String username = document.getElementsByTagName("username").item(0).getFirstChild().getNodeValue();
        final String password = document.getElementsByTagName("password").item(0).getFirstChild().getNodeValue();
        

        final dbManager db = dbManager.getInstance();
        // verifica stato connessione a DBMS
        if (!db.isConnected()) {
            throw new WebApplicationException("DBMS server error!", 500);
        }
        long id = -1;
        final String sql = "INSERT INTO utenti (Username, Password) VALUES (?,?)";
        try (PreparedStatement statement = db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, username);
            statement.setString(2, password);
            int affectedRows = statement.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new WebApplicationException("DBMS server error!", 500);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new WebApplicationException("DBMS server error!", 500);
        }
        return "Inserimento effettuato. ID: " + id;

    }
}
