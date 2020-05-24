/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import org.w3c.dom.Element;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Path("utenti/login")
public class PostEx {

    XMLUtils myXmlUtils; // string to xml and xml parser

    public PostEx() {
        myXmlUtils = new XMLUtils();
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes("application/xml")
    public String getXmlScheda(String content) {
        String username;
        String pw;
        try {
            Element root = myXmlUtils.loadXmlFromString(content);
            username = root.getElementsByTagName("username").item(0).getTextContent();
            pw = root.getElementsByTagName("password").item(0).getTextContent();
            DbOperations.loginUtente(username, pw);
            return "<messaggio>Login effettuato con successo</messaggio>";
        } catch (Exception e) {
            e.printStackTrace();
            return "<messaggio>Controlla i dati</messaggio><exception>" + e + "</exception>";
        }
    }
}
