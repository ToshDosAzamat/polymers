package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "news_tab")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<NewsTranslation> newsTranslation = new HashSet<>();
}