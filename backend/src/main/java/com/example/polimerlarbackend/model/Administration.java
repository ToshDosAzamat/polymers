package com.example.polimerlarbackend.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "administration_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Administration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "administration", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdministrationTranslation> translations = new HashSet<>();
}