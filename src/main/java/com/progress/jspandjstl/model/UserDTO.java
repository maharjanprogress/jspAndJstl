package com.progress.jspandjstl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{
    private String username;
    private String password;
    private Set<String> roles =new HashSet<>();
}
