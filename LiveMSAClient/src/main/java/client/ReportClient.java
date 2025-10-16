/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author Acer
 */
@RegisterRestClient(baseUri = "http://msaresource:8080/LiveMSAResource/rest/land")
public interface ReportClient {
    
    @ClientHeaderParam(name = "Authorization",value = "{getToken}")
    @GET
    @Path("/districts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDistricts();

    @ClientHeaderParam(name = "Authorization", value = "{getToken}")
    @GET
    @Path("/land/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLands(@PathParam("id") int id);
    
    
    default String getToken() {
        Config config = ConfigProvider.getConfig();
        String token = "Bearer " + config.getValue("jwt", String.class);
        return token;
    }
    
}
