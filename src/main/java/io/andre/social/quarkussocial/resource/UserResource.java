package io.andre.social.quarkussocial.resource;

import io.andre.social.quarkussocial.domain.model.User;
import io.andre.social.quarkussocial.domain.repository.UserRepository;
import io.andre.social.quarkussocial.domain.model.dto.CreateUserRequest;
import io.andre.social.quarkussocial.domain.model.dto.ResponseError;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
//Informa que essa aplicação consome objetos do tipo JSON > isso se aplica a todos os métodos dessa classe
@Produces(MediaType.APPLICATION_JSON) //Informa que a respostas retornam JSON's
public class UserResource {

    @Inject
    Validator validator;

    @Inject
    Logger log;

    @Inject
    public UserRepository userRepository;

    @POST
    @Transactional //anotação necessária para ações que fazem alterações no DB e não somente leitura
    public Response createUser(CreateUserRequest userRequest) {

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if (!violations.isEmpty()) {

            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
        }


        var user = User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .build();

        userRepository.persist(user);
        //return Response.status(201).build();
        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(user)
                .build();
    }

    @GET
    public Response listAllUsers() {
        log.info("listAllUser was used");
        PanacheQuery<User> allUsers = userRepository.findAll();
        return Response
                .ok(allUsers.list())
                .build();

    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id) {

        var user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user);
            return Response
                    .noContent()
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Transactional /*dentro do contexto de transactional não é necessário fazer um persist() pois após a finalização do método
    as alterações serão comitadas no banco de dados */
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData) {

        User user = userRepository.findById(id);

        if (user != null) {
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            //user.persist(); ------------> verificar comentário das Linhas 63 e 64
            return Response
                    .noContent()
                    .build();

        }
        return Response.status(Response.Status.NOT_FOUND).build();


    }


}
