/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.services;

import javax.ejb.EJB;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.beans.SportsBean;
import nu.te4.support.User;

@Path("/")
public class SportService {

    @EJB
    SportsBean sportsbean;

    @GET
    @Path("games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames(@Context HttpHeaders httpHeaders) {
        if (!User.authoricate(httpHeaders)) {
            return Response.status(401).build();
        }
        JsonArray data = sportsbean.getGames();
        if (data == null) {
            return Response.serverError().build();
        }
        return Response.ok(data).build();
    }

    @POST
    @Path("game")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response addGame(String body, @Context HttpHeaders httpHeaders) {
        if (!User.authoricate(httpHeaders)) {
            return Response.status(401).build();
        }
        if (sportsbean.addGame(body)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("game/{id}")
    public Response deleteGame(@PathParam("id") int id, @Context HttpHeaders httpHeaders) {
        if (!User.authoricate(httpHeaders)) {
            return Response.status(401).build();
        }
        if (!sportsbean.deleteGame(id)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @PUT
    @Path("game/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeGame(@PathParam("id") int id, String body, @Context HttpHeaders httpHeaders) {
        if (!User.authoricate(httpHeaders)) {
            return Response.status(401).build();
        }
        if (!sportsbean.changeGame(id, body)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }
    
}