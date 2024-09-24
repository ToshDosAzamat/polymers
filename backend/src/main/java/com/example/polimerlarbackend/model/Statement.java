package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="statement_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "statement", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<StatementTranslation> images = new HashSet<>();
}