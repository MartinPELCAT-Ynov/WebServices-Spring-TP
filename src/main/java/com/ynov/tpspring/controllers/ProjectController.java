package com.ynov.tpspring.controllers;

import com.ynov.tpspring.entities.Message;
import com.ynov.tpspring.entities.Project;
import com.ynov.tpspring.entities.Request;
import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
    public Project getProject(@PathVariable("projectId") Long projectId) {
        return this.projectService.getProject(projectId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Project> getProjects() {
        return this.projectService.getProjects();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Project createProject(@RequestBody Project project) {
        return this.projectService.createProject(project);
    }

    @RequestMapping(value = "/{projectId}", method = RequestMethod.PUT)
    public Project updateProject(@PathVariable("projectId") Long projectId, @RequestBody Project project, Principal principal) {
        return this.projectService.updateProject(projectId, project);
    }

    @RequestMapping(value = "/{projectId}/participants", method = RequestMethod.GET)
    public List<User> getParticipants(@PathVariable("projectId") Long projectId) {
        return this.projectService.getParticipants(projectId);
    }

    @RequestMapping(value = "/{projectId}/subscribers", method = RequestMethod.GET)
    public List<User> getSubscribers(@PathVariable("projectId") Long projectId) {
        return this.projectService.getSubscribers(projectId);
    }

    @RequestMapping(value = "/{projectId}/messages", method = RequestMethod.GET)
    public List<Message> getProjectMessages(@PathVariable("projectId") Long projectId) {
        return this.projectService.getMessages(projectId);
    }

    @RequestMapping(value = "/{projectId}/requests", method = RequestMethod.GET)
    public List<Request> getRequests(@PathVariable("projectId") Long projectId) {
        return this.projectService.getRequests(projectId);
    }

    @RequestMapping(value = "/{projectId}/subscribe", method = RequestMethod.POST)
    public Project subscribe(@PathVariable("projectId") Long projectId, Principal principal) {
        return this.projectService.subscribe(projectId, principal.getName());
    }

    @RequestMapping(value = "/{projectId}/unsubscribe", method = RequestMethod.POST)
    public Project unsubscribe(@PathVariable("projectId") Long projectId, Principal principal) {
        return this.projectService.unsubscribe(projectId, principal.getName());
    }


}
