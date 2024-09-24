package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "labs_translation_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

}