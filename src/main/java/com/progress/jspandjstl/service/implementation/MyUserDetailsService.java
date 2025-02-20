package com.progress.jspandjstl.service.implementation;

import com.progress.jspandjstl.model.UserDTO;
import com.progress.jspandjstl.model.UserPrinciple;
import com.progress.jspandjstl.model.Users;
import com.progress.jspandjstl.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = repo.findByUsername(username);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrinciple(users.get());
    }
}
