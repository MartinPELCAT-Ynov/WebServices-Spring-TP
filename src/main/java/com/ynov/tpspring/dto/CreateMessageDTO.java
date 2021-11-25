package com.ynov.tpspring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ynov.tpspring.entities.Message;

@JsonIgnoreProperties({"user", "id", "likeCount"})
public class CreateMessageDTO extends Message {
}
