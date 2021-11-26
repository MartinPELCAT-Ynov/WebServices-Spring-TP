package com.ynov.tpspring.services;

import com.ynov.tpspring.entities.Center;
import com.ynov.tpspring.repositories.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterService {

    @Autowired
    private CenterRepository centerRepository;

    public Center createCenter(Center center) {
        return centerRepository.save(center);
    }
}
