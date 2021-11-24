package com.ynov.tpspring.services;

import com.ynov.tpspring.entities.Message;
import com.ynov.tpspring.entities.Project;
import com.ynov.tpspring.entities.Request;
import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.repositories.ProjectRepository;
import com.ynov.tpspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Message> getMessages(Long projectId) {
        return projectRepository.findById(projectId).orElse(null).getMessages();
    }

    public Project getProject(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long projectId, Project project) {
        project.setId(projectId);
        return projectRepository.save(project);
    }

    public List<User> getParticipants(Long projectId) {
        return projectRepository.findById(projectId).orElse(null).getParticipants();
    }

    public List<User> getSubscribers(Long projectId) {
        return projectRepository.findById(projectId).orElse(null).getSubscribers();
    }

    public List<Request> getRequests(Long projectId) {
        return projectRepository.findById(projectId).orElse(null).getRequests();
    }

    public Project subscribe(Long projectId, String username) {
        Project project = projectRepository.findById(projectId).orElse(null);
        User user = userRepository.findByUsername(username);
        project.getSubscribers().add(user);
        projectRepository.save(project);
        return project;
    }

    public Project unsubscribe(Long projectId, String username) {
        Project project = projectRepository.findById(projectId).orElse(null);
        User user = userRepository.findByUsername(username);
        project.getSubscribers().remove(user);
        projectRepository.save(project);
        return project;
    }
}
