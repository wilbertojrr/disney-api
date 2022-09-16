package com.alkemy.disneyApi.auth.service;

import com.alkemy.disneyApi.auth.dto.UserDTO;
import com.alkemy.disneyApi.auth.entity.UserEntity;
import com.alkemy.disneyApi.auth.exception.UserAlreadyExistException;
import com.alkemy.disneyApi.auth.repository.UserRepository;
import com.alkemy.disneyApi.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IEmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean checkIfUserExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public boolean save(UserDTO userDTO) throws UserAlreadyExistException {
        if (checkIfUserExist(userDTO.getUsername())) {
            throw new UserAlreadyExistException("User already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity = userRepository.save(userEntity);

        if (userEntity != null) {
            emailService.sendEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }
}
