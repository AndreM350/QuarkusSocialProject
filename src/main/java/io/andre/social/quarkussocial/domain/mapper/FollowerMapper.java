package io.andre.social.quarkussocial.domain.mapper;

import io.andre.social.quarkussocial.domain.model.Follower;
import io.andre.social.quarkussocial.domain.model.dto.FollowerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface FollowerMapper {

    FollowerMapper MAPPER = Mappers.getMapper(FollowerMapper.class);


    List<FollowerResponse> toFollowerResponseList(List<Follower> followerList);

}
