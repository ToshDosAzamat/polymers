package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="statement_translation_tab")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class StatementTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "statement_id")
    private Statement statement;

    @ManyToOne
    @JoinColumn(name = "council_id")
    private Council council;

    @Column(nullable = false)
    private String language;


    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "statements_images",
            joinColumns = @JoinColumn(name = "statement_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
    private Set<Image> images;
}