package com.ynov.tpspring.services;

import com.ynov.tpspring.entities.Request;
import com.ynov.tpspring.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request getRequestById(Long id) {
        return requestRepository.findById(id).get();
    }

    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    public Request updateRequest(Long requestId, Request request) {
        request.setId(requestId);
        return requestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
}
