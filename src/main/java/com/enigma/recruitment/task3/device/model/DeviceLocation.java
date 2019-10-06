package com.enigma.recruitment.task3.device.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class DeviceLocation {
    private static final String GENERATOR = "DEVICE_LOCATION_ID_GEN";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
    @SequenceGenerator(name = GENERATOR, sequenceName = "DEVICE_LOCATION_IDX_SEQ", allocationSize = 1)
    private Long id;

    private Long deviceId;

    private Long latitude;

    private Long longitude;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
