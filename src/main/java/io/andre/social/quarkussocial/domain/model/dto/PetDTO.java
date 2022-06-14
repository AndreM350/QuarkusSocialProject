package io.andre.social.quarkussocial.domain.model.dto;

import io.andre.social.quarkussocial.domain.model.Pet;
import lombok.Data;

@Data
public class PetDTO {

    private String name;

    public PetDTO(Pet pet) {
        this.name = pet.getName();
    }
}
