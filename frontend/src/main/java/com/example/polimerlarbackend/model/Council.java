package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="council_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Council {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "council", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<CouncilTranslation> councilTranslations = new HashSet<>();

    @OneToMany(mappedBy = "council", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<StatementTranslation> images = new HashSet<>();

}
