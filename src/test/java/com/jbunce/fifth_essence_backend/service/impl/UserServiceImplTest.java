package com.jbunce.fifth_essence_backend.service.impl;

import com.jbunce.fifth_essence_backend.persistance.entities.Role;
import com.jbunce.fifth_essence_backend.persistance.entities.User;
import com.jbunce.fifth_essence_backend.persistance.repositories.IUserRepository;
import com.jbunce.fifth_essence_backend.service.IRoleService;
import com.jbunce.fifth_essence_backend.web.dtos.BaseResponse;
import com.jbunce.fifth_essence_backend.web.dtos.request.RoleRequest;
import com.jbunce.fifth_essence_backend.web.dtos.request.UserRequest;
import com.jbunce.fifth_essence_backend.web.dtos.response.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private IUserRepository repository;

    @Mock
    private IRoleService roleService;

    @Mock
    private PasswordEncoder encoder;

    private User user;
    private UserRequest request;

    @BeforeEach
    public void init() {
        user = createUserDummy();
        request = userRequestDummy();
    }

    @Test
    void givenUserIdOk_whenFindById_thenReturnUser() {
        when(repository.findById(anyLong())).thenAnswer(i -> {
           if (i.getArgument(0).equals(1L)) {
               return Optional.of(user);
           } else {
               throw new EntityNotFoundException("User not found");
           }
        });

        BaseResponse response = userService.findById(1L);
        UserResponse userResponse = (UserResponse) response.getData();

        assertNotNull(userResponse);
        assertEquals(1L, userResponse.id());
    }

    @Test
    void givenUserRequestOk_whenCreateUser_thenAllOK() {
        when(encoder.encode(anyString()))
                .thenAnswer(i -> new BCryptPasswordEncoder(10).encode(i.getArgument(0)));

        when(repository.save(any(User.class))).thenAnswer(i -> {
            User user = i.getArgument(0);
            user.setId(1L);
            return user;
        });

        BaseResponse response = userService.create(request);
        UserResponse userResponse = (UserResponse) response.getData();

        assertNotNull(userResponse);
        assertEquals(1L, userResponse.id());
        assertEquals(request.phoneNumber(), userResponse.phoneNumber());
        assertEquals(request.username(), userResponse.username());
        assertEquals(request.email(), userResponse.email());
    }

    @Test
    void givenAPhoneNumber_whenFindByPhoneNumber_thenReturnTheCorrectUser() {
        when(repository.findByPhoneNumber(anyString())).thenAnswer(i -> {
            if (i.getArgument(0).equals("9614106069")) {
                return Optional.of(user);
            } else {
                throw new EntityNotFoundException("User not found");
            }
        });

        User user = userService.findByPhoneNumber("9614106069");

        assertNotNull(user);
        assertEquals(user.getPhoneNumber(), "9614106069");
    }

    private UserRequest userRequestDummy() {
        return new UserRequest(
                "Nuricumbo",
                "9614106069",
                "sr.conejo.uwu@gmail.com",
                "ContraChingona1234",
                Set.of(new RoleRequest("ROLE_USER"))
        );
    }

    private User createUserDummy() {
        User userDummy = new User();

        userDummy.setId(1L);
        userDummy.setUsername("Nuricumbo");
        userDummy.setPhoneNumber("9614106069");
        userDummy.setEmail("sr.conejo.uwu@gmail.com");
        userDummy.setPassword("ContraChingona1234");
        userDummy.setRoles(Set.of(roleDummy()));

        return userDummy;
    }

    private Role roleDummy() {
        Role role = new Role();

        role.setId(1L);
        role.setName("ROLE_USER");

        return role;
    }

}