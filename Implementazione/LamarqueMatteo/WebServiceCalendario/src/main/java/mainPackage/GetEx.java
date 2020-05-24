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
    public String getImpegno() {
        // connect to the db, create and execute a query, get the result, parse and print it
        return "<impegni>" + DbOperations.printImpegni(DbOperations.getImpegno()) + "</impegni>";
    }
}