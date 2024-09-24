package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "own_service_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "ownService", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<OwnServiceTranslation> translations = new HashSet<>();


}