package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name= "devices_translation_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeviceTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 300)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
}
