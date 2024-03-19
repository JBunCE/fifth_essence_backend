package com.jbunce.fifth_essence_backend.service.impl;

import com.jbunce.fifth_essence_backend.persistance.entities.Role;
import com.jbunce.fifth_essence_backend.persistance.entities.User;
import com.jbunce.fifth_essence_backend.persistance.repositories.IUserRepository;
import com.jbunce.fifth_essence_backend.service.IRoleService;
import com.jbunce.fifth_essence_backend.service.IUserService;
import com.jbunce.fifth_essence_backend.web.dtos.BaseResponse;
import com.jbunce.fifth_essence_backend.web.dtos.request.UserRequest;
import com.jbunce.fifth_essence_backend.web.dtos.response.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private IRoleService roleService;

    @Override
    public BaseResponse findAll() {
        List<UserResponse> users = repository.findAll()
                .stream().map(this::toUserResponse).toList();

        return BaseResponse.builder()
                .data(users)
                .message("Users")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .statusCode(200).build();
    }

    @Override
    public BaseResponse findById(Long id) {
        UserResponse response = toUserResponse(findOneAndEnsureExists(id));

        return BaseResponse.builder()
                .data(response)
                .message("The user was found with id: " + id)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .statusCode(200).build();
    }

    @Override
    public BaseResponse create(UserRequest request) {
        User savedUser = repository.save(toUser(request));
        UserResponse response = toUserResponse(savedUser);

        roleService.createRoles(request.roles(), savedUser);

        return BaseResponse.builder()
                .data(response)
                .message("The user was created with id: " + savedUser.getId())
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .statusCode(201).build();
    }

    @Override
    public User findOneAndEnsureExists(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public UserResponse toResponse(User user) {
        return toUserResponse(user);
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getPhoneNumber(),
            user.getEmail(),
            user.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
        );
    }

    private User toUser(UserRequest request) {
        User user = new User();

        user.setUsername(request.username());
        user.setPhoneNumber(request.phoneNumber());
        user.setEmail(request.email());
        user.setPassword(request.password());

        return user;
    }
}
