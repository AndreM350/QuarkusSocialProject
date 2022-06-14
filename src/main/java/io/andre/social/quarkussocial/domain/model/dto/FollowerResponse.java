package io.andre.social.quarkussocial.domain.model.dto;

import io.andre.social.quarkussocial.domain.model.Follower;
import io.andre.social.quarkussocial.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowerResponse {

    private Long id;
    private String name;


    public FollowerResponse(Follower follower) {
        this.id = follower.getId();
        this.name = follower.getUser().getName();
    }
}
