package com.ynov.tpspring.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "centers")
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "center")
    private List<User> user;

}
