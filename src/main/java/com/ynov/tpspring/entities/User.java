package com.ynov.tpspring.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "author")
    private List<Project> authoredProjects;


    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;


    @ManyToMany(mappedBy = "participants")
    private List<Project> participatingProjects;

    @ManyToMany(mappedBy = "subscribers")
    private List<Project> subscribedProjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Project> getAuthoredProjects() {
        return authoredProjects;
    }

    public void setAuthoredProjects(List<Project> authoredProjects) {
        this.authoredProjects = authoredProjects;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Project> getParticipatingProjects() {
        return participatingProjects;
    }

    public void setParticipatingProjects(List<Project> participatingProjects) {
        this.participatingProjects = participatingProjects;
    }

    public List<Project> getSubscribedProjects() {
        return subscribedProjects;
    }

    public void setSubscribedProjects(List<Project> subscribedProjects) {
        this.subscribedProjects = subscribedProjects;
    }
}

