package at.htlkaindorf.bsp_207_trainmanagement;

import  at.htlkaindorf.bsp_207_trainmanagement.bl.Train;
import at.htlkaindorf.bsp_207_trainmanagement.bl.TrainDatabaseStatic;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.NoSuchElementException;

@Path("/trains")
public class TrainResource {

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTrains () {
        List<Train> trains = TrainDatabaseStatic.getInstance().getTrains();
        return Response.ok(trains).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getTrain (@PathParam("id") int id) {
        try {
            Train train = TrainDatabaseStatic.getInstance().getById(id);
            return Response.ok(train).build();
        } catch (NoSuchElementException nsee) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrain (Train train) {
        try {
            TrainDatabaseStatic.getInstance().addTrain(train);
            UriBuilder uriBuilder =uriInfo.getAbsolutePathBuilder();
            uriBuilder.path("" + train.getId());
            return Response.created(uriBuilder.build()).entity(train).build();
        } catch (KeyAlreadyExistsException kaee) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response addTrainStation (@PathParam("id") int id, String station) {
        try {
            TrainDatabaseStatic.getInstance().addStation(id, station);
            return Response.ok(station).build();
        } catch (NoSuchElementException nsee) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}