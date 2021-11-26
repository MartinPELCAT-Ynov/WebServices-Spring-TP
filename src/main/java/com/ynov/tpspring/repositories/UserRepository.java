package com.ynov.tpspring.repositories;

import com.ynov.tpspring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select u from User u where u.username = ?1 and u.center.uuid = ?2")
    User findByUsernameAndCenter(String username, String center);
}
