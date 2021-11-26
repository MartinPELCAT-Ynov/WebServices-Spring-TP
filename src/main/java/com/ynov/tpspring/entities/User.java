package com.ynov.tpspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(
                name = "username_center",
                columnNames = {"username", "center_id"}
        )
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String username;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Project> authoredProjects;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Notification> notifications;

    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    private List<Project> participatingProjects;

    @ManyToMany(mappedBy = "subscribers")
    @JsonIgnore
    private List<Project> subscribedProjects;

    @ManyToMany(mappedBy = "likes")
    @JsonIgnore
    private List<Message> likedMessages;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Message> getLikedMessages() {
        return likedMessages;
    }

    public void setLikedMessages(List<Message> likedMessages) {
        this.likedMessages = likedMessages;
    }
}

