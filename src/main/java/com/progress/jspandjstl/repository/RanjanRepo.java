package com.progress.jspandjstl.repository;

import com.progress.jspandjstl.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RanjanRepo extends JpaRepository<Tasks, Integer> {
}
