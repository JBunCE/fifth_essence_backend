package com.jbunce.fifth_essence_backend.persistance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "stream", schema = "fifth_db")
public class Stream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stream_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "stream_data")
    private String streamData;

    @OneToMany(mappedBy = "streamStream")
    private Set<StreamComment> streamComments = new LinkedHashSet<>();

}