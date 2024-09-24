package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="administration_translation_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AdministrationTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "administration_id")
    private Administration administration;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false, length = 50)
    private String fullname;

    @Column(nullable = false, length = 50)
    private String position;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
}
