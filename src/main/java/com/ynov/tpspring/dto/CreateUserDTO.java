package com.ynov.tpspring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynov.tpspring.entities.User;

public class CreateUserDTO extends User {

    @Override
    @JsonIgnore
    public Long getId() {
        return super.getId();
    }
}
