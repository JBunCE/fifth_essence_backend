package com.jbunce.fifth_essence_backend.persistance.repositories;

import com.jbunce.fifth_essence_backend.persistance.entities.pivots.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserResourceRepository extends JpaRepository<UserResource, Long> {
}