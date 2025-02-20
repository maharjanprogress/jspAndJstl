package com.progress.jspandjstl.repository;

import com.progress.jspandjstl.model.Tasks;
import com.progress.jspandjstl.model.UserDTO;
import com.progress.jspandjstl.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);

}
