package com.ynov.tpspring.repositories;

import com.ynov.tpspring.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Center, String> {
}
