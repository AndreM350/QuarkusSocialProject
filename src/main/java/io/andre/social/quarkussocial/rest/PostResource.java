package io.andre.social.quarkussocial.rest;

import io.andre.social.quarkussocial.domain.model.Post;
import io.andre.social.quarkussocial.domain.model.User;
import io.andre.social.quarkussocial.domain.repository.PostRepository;
import io.andre.social.quarkussocial.domain.repository.UserRepository;
import io.andre.social.quarkussocial.rest.dto.CreatePostRequest;
import io.andre.social.quarkussocial.rest.dto.PostResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users/{userId}/posts")
public class PostResource {

    @Inject
    UserRepository userRepository;

    @Inject
    PostRepository postRepository;

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest request){
        System.out.println("Método savePost foi acionado");


        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(request.getText());
        post.setUser(user);
        post.setDateTime(LocalDateTime.now()); //Isso pode ser feito de n maneiras
        postRepository.persist(post);
        return Response.status(Response.Status.CREATED).build();
    }


    @GET
    public Response listPost( @PathParam("userId") Long userId ){
        System.out.println("Método listPost foi acionado");

        User user = userRepository.findById(userId);
        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        /*query dinâmica sendo o "user" = atributo da entidade e o objeto user seria um this.user(pesquisa DESSE usuário */
        var thisUserPosts = postRepository.find("user", Sort.by("dateTime", Sort.Direction.Descending), user);
        var list = thisUserPosts.list();
        var postResponseList = list.stream().map(post -> PostResponse.fromEntity(post)).collect(Collectors.toList());
//        var postResponseList2 = list.stream().map(PostResponse::fromEntity).collect(Collectors.toList());

        return Response.ok(postResponseList).build();
    }

}
