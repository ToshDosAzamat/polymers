package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="news_translation_tab")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class NewsTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false, length = 400)
    private String title;

    @Column(nullable = false)
    private String text;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "news_images",
            joinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
    private Set<Image> images;
}
