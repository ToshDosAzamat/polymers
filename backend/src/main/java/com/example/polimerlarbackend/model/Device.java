package com.example.polimerlarbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="devices_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<DeviceTranslation> devices = new HashSet<>();
}
