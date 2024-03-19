package com.jbunce.fifth_essence_backend.service;

import com.jbunce.fifth_essence_backend.web.dtos.BaseResponse;
import com.jbunce.fifth_essence_backend.web.dtos.request.PublicationRequest;

public interface IPublicationService {

    BaseResponse findAll();
    BaseResponse findById(Long id);
    BaseResponse create(PublicationRequest request);
}
