package io.andre.social.quarkussocial.resource;

import io.andre.social.quarkussocial.domain.mapper.FollowerMapper;
import io.andre.social.quarkussocial.domain.model.Follower;
import io.andre.social.quarkussocial.domain.model.User;
import io.andre.social.quarkussocial.domain.model.dto.FollowerRequest;
import io.andre.social.quarkussocial.domain.model.dto.FollowerResponse;
import io.andre.social.quarkussocial.domain.model.dto.FollowersPerUserResponse;
import io.andre.social.quarkussocial.domain.repository.FollowerRepository;
import io.andre.social.quarkussocial.domain.repository.UserRepository;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {

    @Inject
    FollowerMapper mapper;

    @Inject
    Logger log;

    @Inject
    UserRepository userRepository;

    @Inject
    FollowerRepository followerRepository;

    @PUT
    @Transactional
    public Response followUser(@PathParam("userId") Long userId, FollowerRequest request) {

        if (userId.equals(request.getFollowerId())) { //evitar que o próprio usuário se siga
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("Você não pode seguir você mesmo")
                    .build();
        }

        //user do path será seguido pelo user do request(json)
        var user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        var follower = userRepository.findById(request.getFollowerId());
        boolean follows = followerRepository.follows(follower, user);

        if (!follows) {

            var entity = new Follower();
            entity.setUser(user);
            entity.setFollower(follower);
            log.info("O Usuário de id: " + follower.getId() + " seguiu o usuário " + user.getId());
            followerRepository.persist(entity);
        }

        return Response.status(Response.Status.NO_CONTENT).build();


    }

    @GET
    public Response listFollowers(@PathParam("userId") Long userId) {

        var user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var followersList = followerRepository.findByUser(userId);

        var followersPerUserResponse = new FollowersPerUserResponse();

        followersPerUserResponse.setFollowersCount(followersList.size()); //setar a qnt de seguidores totais

//        List<FollowerResponse> followerResponseList = followersList
//                .stream()
//                .map(FollowerResponse::new)
//                .collect(Collectors.toList());
//
//        followersPerUserResponse.setContent(followerResponseList);

        //---------------------------------------------------------------------------

//        List<FollowerResponse> followerResponseList2 = mapper.toFollowerResponseList(followersList);

        List<FollowerResponse> followerResponseList2 = followersList
                .stream()
                .map(follower -> new FollowerResponse(follower))
                .collect(Collectors.toList());

        followersPerUserResponse.setContent(followerResponseList2);
        return Response.ok(followersPerUserResponse).build();

    }















}
