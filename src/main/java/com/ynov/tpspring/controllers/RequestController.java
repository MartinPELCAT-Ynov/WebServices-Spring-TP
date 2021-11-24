package com.ynov.tpspring.controllers;

import com.ynov.tpspring.entities.Request;
import com.ynov.tpspring.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    RequestService requestService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Request> getRequests() {
        return requestService.getAllRequests();
    }

    @RequestMapping(value = "/{requestId}", method = RequestMethod.GET)
    public Request getRequest(@PathVariable("requestId") Long requestId) {
        return requestService.getRequestById(requestId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Request createRequest(Request request) {
        return requestService.createRequest(request);
    }

    @RequestMapping(value = "/{requestId}", method = RequestMethod.PUT)
    public Request updateRequest(@PathVariable("requestId") Long requestId, Request request) {
        return requestService.updateRequest(requestId, request);
    }

    @RequestMapping(value = "/{requestId}", method = RequestMethod.DELETE)
    public void deleteRequest(@PathVariable("requestId") Long requestId) {
        requestService.deleteRequest(requestId);
    }
}

