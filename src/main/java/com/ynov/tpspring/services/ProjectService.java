package com.ynov.tpspring.services;

import com.ynov.tpspring.entities.Message;
import com.ynov.tpspring.entities.Project;
import com.ynov.tpspring.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

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
}
