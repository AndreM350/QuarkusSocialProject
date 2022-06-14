package io.andre.social.quarkussocial.domain.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.Objects;

//public class User extends PanacheEntityBase
@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(as = User.class)
@Schema(description = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "nome do usuário", required = true)
    @JsonAlias({"nome do usuário"})
    @Column(name = "name")
    private String name;

    @Schema(description = "idade do usuário", required = true)
    @JsonAlias({"idade do usuário"})
    @Column(name = "age")
    private Integer age;


}
