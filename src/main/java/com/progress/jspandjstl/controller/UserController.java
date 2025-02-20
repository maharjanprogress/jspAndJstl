package com.progress.jspandjstl.controller;

import com.progress.jspandjstl.model.LoginDTO;
import com.progress.jspandjstl.model.UserDTO;
import com.progress.jspandjstl.model.Users;
import com.progress.jspandjstl.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "http://172.19.96.1:5500")
public class UserController {

    @Autowired
    UserService service;

    @ResponseBody
    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO dto) {
        return service.register(dto);
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return service.verify(dto);
    }
}
