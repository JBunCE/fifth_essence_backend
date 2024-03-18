package com.jbunce.fifth_essence_backend.persistance.repositories;

import com.jbunce.fifth_essence_backend.persistance.entities.pivots.EventResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventResourceRepository extends JpaRepository<EventResource, Long> {
}