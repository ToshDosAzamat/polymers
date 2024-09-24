package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="council_translation_tab")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CouncilTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "council_id")
    private Council council;

    @ManyToOne
    @JoinColumn(name = "statement_id")
    private Statement statement;

    @Column(nullable = false, length = 5)
    private String language;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
}
