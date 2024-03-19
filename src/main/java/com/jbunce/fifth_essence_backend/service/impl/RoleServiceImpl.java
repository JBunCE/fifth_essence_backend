package com.jbunce.fifth_essence_backend.service.impl;

import com.jbunce.fifth_essence_backend.persistance.entities.Role;
import com.jbunce.fifth_essence_backend.persistance.entities.User;
import com.jbunce.fifth_essence_backend.persistance.repositories.IRoleRepository;
import com.jbunce.fifth_essence_backend.service.IRoleService;
import com.jbunce.fifth_essence_backend.web.dtos.request.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository repository;

    @Override
    public void createRoles(Set<RoleRequest> requests, User user) {
        List<Role> roles = requests.stream()
                .map(role -> toRole(role, user)).toList();

        repository.saveAll(roles);
    }

    private Role toRole(RoleRequest request, User user) {
        Role role = new Role();

        role.setName(request.name());
        role.setUsersUser(user);

        return role;
    }
}
