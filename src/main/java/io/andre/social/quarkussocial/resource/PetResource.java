package io.andre.social.quarkussocial.resource;

import io.andre.social.quarkussocial.domain.model.Pet;
import io.andre.social.quarkussocial.domain.model.dto.PetDTO;
import io.andre.social.quarkussocial.domain.service.PetService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/pets")
public class PetResource {

    @Inject
    @RestClient
    PetService petService;

    //http://localhost:8080/pets/acharPets?status=available
    //https://petstore.swagger.io/
    @GET
    @Path("acharPets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PetDTO> metodo(@QueryParam("status") String status) {

        List<Pet> petList = petService.findByStatus(status);
        return petList.stream().map(PetDTO::new).collect(Collectors.toList());



//        return petService.findByStatus(status);
    }

    @GET
    @Path("/helloPet")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "ola";
    }

}
