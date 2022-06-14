package io.andre.social.quarkussocial.domain.service;

import io.andre.social.quarkussocial.domain.model.Pet;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/pet")
@RegisterRestClient(configKey="pet-api")
@ApplicationScoped
public interface PetService {


    //io.andre.social.quarkussocial.domain.service.PetService/mp-rest/url=https://petstore.swagger.io/v2
    //io.andre.social.quarkussocial.domain.service.PetService/mp-rest/scope=javax.inject.Singleton
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("findByStatus")
    public List<Pet> findByStatus(@QueryParam("status") String status);



}
