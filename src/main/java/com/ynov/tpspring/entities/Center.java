package com.ynov.tpspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "centers")
public class Center {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String uuid = UUID.randomUUID().toString();

    @Column
    private String name;

    @OneToMany(mappedBy = "center")
    @JsonIgnore
    private List<User> user;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
