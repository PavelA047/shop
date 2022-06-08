package com.example.shoptest.service;

import com.example.shoptest.dto.UserDto;
import com.example.shoptest.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDto userDto);
    void save(User user);
    List<UserDto> getAll();

    void updateProfile(UserDto userDto);

    User findByName(String name);
}
