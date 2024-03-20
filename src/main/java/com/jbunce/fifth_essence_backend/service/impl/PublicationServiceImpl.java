package com.jbunce.fifth_essence_backend.service.impl;

import com.jbunce.fifth_essence_backend.persistance.entities.Publication;
import com.jbunce.fifth_essence_backend.persistance.entities.User;
import com.jbunce.fifth_essence_backend.persistance.repositories.IPublicationRepository;
import com.jbunce.fifth_essence_backend.service.IPublicationService;
import com.jbunce.fifth_essence_backend.service.IUserService;
import com.jbunce.fifth_essence_backend.web.dtos.BaseResponse;
import com.jbunce.fifth_essence_backend.web.dtos.request.PublicationRequest;
import com.jbunce.fifth_essence_backend.web.dtos.response.PublicationResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationServiceImpl implements IPublicationService {

    @Autowired
    private IPublicationRepository repository;

    @Autowired
    private IUserService userService;

    @Override
    public BaseResponse findAll() {
        List<PublicationResponse> responses = repository.findAll()
                .stream().map(this::toPublicationResponse).toList();

        return BaseResponse.builder()
                .data(responses)
                .message("Publications")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .statusCode(200).build();
    }

    @Override
    public BaseResponse findById(Long id) {
        Publication publication = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        PublicationResponse response = toPublicationResponse(publication);

        return BaseResponse.builder()
                .data(response)
                .message("Publication with id: " + publication.getId())
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .statusCode(200).build();
    }

    @Override
    public BaseResponse create(PublicationRequest request) {
        Publication savedPublication = repository.save(toPublication(request));
        PublicationResponse response = toPublicationResponse(savedPublication);

        return BaseResponse.builder()
                .data(response)
                .message("The publication was created with id: " + savedPublication.getId())
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .statusCode(201).build();
    }

    private PublicationResponse toPublicationResponse(Publication publication) {
        return new PublicationResponse(
                publication.getId(),
                userService.toResponse(publication.getUsersUser()),
                publication.getTitle(),
                publication.getBody()
        );
    }

    private Publication toPublication(PublicationRequest request) {
        User user = userService.findOneAndEnsureExists(request.authorId());
        Publication publication = new Publication();

        publication.setUsersUser(user);
        publication.setTitle(request.title());
        publication.setBody(request.body());

        return publication;
    }
}
