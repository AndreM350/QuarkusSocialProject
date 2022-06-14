package io.andre.social.quarkussocial.domain.repository;

import io.andre.social.quarkussocial.domain.model.Follower;
import io.andre.social.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {

    public boolean follows (User follower, User user){
        var params = Parameters.with("follower", follower).and("user", user).map();
        var query = find("follower = :follower and user = :user ", params);
        var result = query.firstResultOptional();
        return result.isPresent(); //se achar retorna verdadeiro
    }

    public List<Follower> findByUser(Long userId){
        PanacheQuery<Follower> query = find("user.id", userId);
        return query.list();
    }

}
