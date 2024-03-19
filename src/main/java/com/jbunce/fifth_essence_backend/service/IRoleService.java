package com.jbunce.fifth_essence_backend.service;

import com.jbunce.fifth_essence_backend.persistance.entities.User;
import com.jbunce.fifth_essence_backend.web.dtos.request.RoleRequest;

import java.util.List;
import java.util.Set;

public interface IRoleService {

    void createRoles(Set<RoleRequest> requests, User user);

}
