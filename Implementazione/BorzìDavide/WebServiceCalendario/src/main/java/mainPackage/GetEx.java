package mainPackage;

import javax.ws.rs.core.*;
import javax.ws.rs.*;

@ApplicationPath("/")
@Path("/")
public class GetEx extends Application {

    public GetEx() {
        super();
    }

    @GET
    @Produces("text/xml")
    @Path("/impegni")
    public String getImpegnoNome(@PathParam(value="nome") String nome) {
        // connect to the db, create and execute a query, get the result, parse and print it
        DbOperations.setNameFilter(nome);
        return "<impegni>" + DbOperations.printImpegni(DbOperations.getImpegno()) + "</impegni>";
    }
    
    @GET
    @Produces("text/xml")
    @Path("/impegni")
    public String getImpegnoData(@PathParam(value="data") String data){
        // connect to the db, create and execute a query, get the result, parse and print it
        DbOperations.setDateFilter(data);
        return "<impegni>" + DbOperations.printImpegni(DbOperations.getImpegno()) + "</impegni>";
    }
}