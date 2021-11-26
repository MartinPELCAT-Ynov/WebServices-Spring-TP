package com.ynov.tpspring.repositories;

import com.ynov.tpspring.entities.ProjectParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectParticipationRepository extends JpaRepository<ProjectParticipation, Long> {
}
