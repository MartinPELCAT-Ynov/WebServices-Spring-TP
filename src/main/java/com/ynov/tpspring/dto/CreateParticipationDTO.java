package com.ynov.tpspring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynov.tpspring.entities.ProjectParticipation;

public class CreateParticipationDTO extends ProjectParticipation {

    @Override
    @JsonIgnore
    public Long getId() {
        return super.getId();
    }

}
