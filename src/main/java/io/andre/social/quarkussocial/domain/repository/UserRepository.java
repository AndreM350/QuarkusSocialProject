package io.andre.social.quarkussocial.domain.repository;

import io.andre.social.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped //Anotação da injeção de dependência - instanciador
public class UserRepository implements PanacheRepository<User> {
}
