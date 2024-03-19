package com.jbunce.fifth_essence_backend.web.controllers.advice;

import com.jbunce.fifth_essence_backend.service.IUserService;
import com.jbunce.fifth_essence_backend.web.dtos.BaseResponse;
import com.jbunce.fifth_essence_backend.web.dtos.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllUsers() {
        return service.findAll().apply();
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable Long id) {
        return service.findById(id).apply();
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserRequest request) {
        return service.create(request).apply();
    }

}
