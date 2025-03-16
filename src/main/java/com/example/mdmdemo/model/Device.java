package com.example.mdmdemo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", unique = true, nullable = false)
    private Long deviceId;


    @Column(name = "device_name", nullable = false)
    private String deviceName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp(source = SourceType.VM)
    private LocalDateTime createdAt;


}
