/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import ejb.ReportBeanLocal;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Acer
 */
@RolesAllowed({"Admin"})
@Path("/land")
public class LandService {
    @EJB ReportBeanLocal local;
    
    @GET
    @Path("/districts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDistricts(){
        return Response.ok(local.getDistricts()).build();
    }

    @GET
    @Path("/land/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDistricts(@PathParam("id") int id){
        return Response.ok(local.getFertileLandByDistrict(id)).build();
    }
}
