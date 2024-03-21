package com.jbunce.fifth_essence_backend.service.impl;

import com.jbunce.fifth_essence_backend.persistance.entities.Publication;
import com.jbunce.fifth_essence_backend.persistance.entities.Role;
import com.jbunce.fifth_essence_backend.persistance.entities.User;
import com.jbunce.fifth_essence_backend.persistance.repositories.IPublicationRepository;
import com.jbunce.fifth_essence_backend.service.IUserService;
import com.jbunce.fifth_essence_backend.web.dtos.BaseResponse;
import com.jbunce.fifth_essence_backend.web.dtos.request.PublicationRequest;
import com.jbunce.fifth_essence_backend.web.dtos.response.PublicationResponse;
import com.jbunce.fifth_essence_backend.web.dtos.response.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublicationServiceImplTest {

    @InjectMocks
    private PublicationServiceImpl publicationService;

    @Mock
    private IPublicationRepository repository;

    @Mock
    private IUserService userService;

    private Publication publication;
    private PublicationRequest publicationRequest;

    @BeforeEach
    void init() {
        publication = createPublicationDummy();
        publicationRequest = publicationRequestDummy();
    }

    @Test
    void givenIdOk_whenFindById_thenAllOk() {
        when(repository.findById(anyLong())).thenAnswer(i -> {
            if (i.getArgument(0).equals(1L)) {
                return Optional.of(publication);
            } else {
                throw new EntityNotFoundException();
            }
        });

        BaseResponse response = publicationService.findById(1L);
        PublicationResponse publicationResponse = (PublicationResponse) response.getData();

        assertNotNull(response);
        assertEquals(1L, publicationResponse.id());
    }

    @Test
    void givenPublicationRequest_whenCreateUserOk_ThenAllOk() {
        when(userService.findOneAndEnsureExists(anyLong())).thenAnswer(i -> createUserDummy());

        when(userService.toResponse(any(User.class))).thenAnswer(i -> {
            User user = i.getArgument(0);
            return new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getPhoneNumber(),
                    user.getEmail()
            );
        });

        when(repository.save(any(Publication.class))).thenAnswer(i -> {
            Publication publication = i.getArgument(0);
            publication.setId(1L);
            return publication;
        });

        BaseResponse response = publicationService.create(publicationRequest);
        PublicationResponse publicationResponse = (PublicationResponse) response.getData();

        assertNotNull(response);
        assertEquals(1L, publicationResponse.id());
        assertEquals(publicationRequest.title(), publicationResponse.title());
        assertEquals(publicationRequest.body(), publicationResponse.body());
    }

    private Publication createPublicationDummy() {
        Publication publication = new Publication();

        publication.setId(1L);
        publication.setTitle("Title");
        publication.setBody("Description");
        publication.setUsersUser(createUserDummy());

        return publication;
    }

    private PublicationRequest publicationRequestDummy() {
        return new PublicationRequest(
                1L,
                "Title",
                "Description"
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