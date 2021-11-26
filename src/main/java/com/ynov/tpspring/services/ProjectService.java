package com.ynov.tpspring.services;

import com.ynov.tpspring.dto.CreateParticipationDTO;
import com.ynov.tpspring.entities.*;
import com.ynov.tpspring.repositories.ProjectParticipationRepository;
import com.ynov.tpspring.repositories.ProjectRepository;
import com.ynov.tpspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectParticipationRepository projectParticipationRepository;

    @Autowired
    private NotificationService notificationService;

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

    public ProjectParticipation createJoinRequest(CreateParticipationDTO participation, User user, Project project) {
        ProjectParticipation projectParticipation = new ProjectParticipation();
        projectParticipation.setProject(project);
        projectParticipation.setUser(user);
        projectParticipation.setValidated(false);
        projectParticipation.setPatientCount(participation.getPatientCount());
        project.getProjectParticipations().add(projectParticipation);
        projectRepository.save(project);
        Notification notification = new Notification("You have a new request to join your project" + project.getName(), NotificationType.PARTICIPATION_REQUESTED);
        notificationService.sendNotification(notification, project.getAuthor());
        return projectParticipation;
    }

    public Boolean isProjectOwner(Long projectId, User user) {
        Project project = projectRepository.findById(projectId).orElse(null);
        return project.getAuthor().equals(user);
    }

    public Boolean isProjectOwner(Long projectId, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Project project = projectRepository.findById(projectId).orElse(null);
        return project.getAuthor().equals(user);
    }

    public ProjectParticipation acceptRequest(Long requestId) {
        ProjectParticipation projectParticipation = projectParticipationRepository.findById(requestId).orElse(null);
        projectParticipation.setValidated(true);
        Project project = projectParticipation.getProject();
        project.getParticipants().add(projectParticipation.getUser());
        Integer identifiedPatients = project.getIdentifiedPatients();
        identifiedPatients += projectParticipation.getPatientCount();
        project.setIdentifiedPatients(identifiedPatients);
        projectRepository.save(project);
        Notification notification = new Notification("You have been accepted to the project " + project.getName(), NotificationType.PARTICIPATION_ACCEPTED);
        notificationService.sendNotification(notification, projectParticipation.getUser());
        return projectParticipationRepository.save(projectParticipation);
    }
}
