package com.example.polimerlarbackend.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "labs_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<LabTranslation> translations = new HashSet<>();

    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<DeviceTranslation> devices = new HashSet<>();

}
