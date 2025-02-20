package com.progress.jspandjstl.repository;

import com.progress.jspandjstl.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(String name);
}
