package com.jbunce.fifth_essence_backend.service;

import com.jbunce.fifth_essence_backend.persistance.entities.User;
import com.jbunce.fifth_essence_backend.web.dtos.BaseResponse;
import com.jbunce.fifth_essence_backend.web.dtos.request.UserRequest;
import com.jbunce.fifth_essence_backend.web.dtos.response.UserResponse;

public interface IUserService {

    BaseResponse findAll();
    BaseResponse findById(Long id);
    BaseResponse create(UserRequest request);
    User findOneAndEnsureExists(Long id);
    User findByPhoneNumber(String phoneNumber);
    UserResponse toResponse(User user);
}
