package io.andre.social.quarkussocial.domain.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Name is Required")
    private String name;
    @NotNull(message = "Age is Required")
    private Integer age;


}
