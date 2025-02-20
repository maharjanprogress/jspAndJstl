package com.progress.jspandjstl.service.implementation;

import com.progress.jspandjstl.model.*;
import com.progress.jspandjstl.repository.RoleRepo;
import com.progress.jspandjstl.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo rrepo;

    @Autowired
    AuthenticationManager authmanager;


    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    @Autowired
    private RoleRepo roleRepo;

    public UserDTO register(UserDTO user) {
        if (repo.findByUsername(user.getUsername()).isEmpty()) {
            user.setPassword(encoder.encode(user.getPassword()));
            Set<Roles> roles = user.getRoles().stream()
                    .map(roleName -> roleRepo.findByName(roleName)
                            .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            Users users = new Users();
            users.setUsername(user.getUsername());
            users.setPassword(user.getPassword());
            users.setRoles(roles);

            repo.save(users); // Save the task if it doesn't exist
        } else {
            System.out.println("Task with id " + user.getUsername() + " already exists.");
            user.setUsername("this userid already exists");
        }
        return user;
    }

    public Users getTask(int userId) {
        return repo.findById(userId).orElse(new Users(userId, "no User Found", "idk the unknown mans password",new HashSet<>()));
    }

    public String verify(LoginDTO dto) {
        Authentication authentication = authmanager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(dto.getUsername());
        }
        else return "fail";
    }
}
