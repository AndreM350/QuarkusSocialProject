package io.andre.social.quarkussocial.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FollowersPerUserResponse {

    @JsonAlias({"xxx"})
    private Integer followersCount;
    private List<FollowerResponse> content;

}
