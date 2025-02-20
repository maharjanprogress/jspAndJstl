package com.progress.jspandjstl.repository;

import com.progress.jspandjstl.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Tasks, Integer> {
}
