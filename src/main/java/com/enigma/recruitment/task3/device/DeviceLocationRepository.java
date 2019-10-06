package com.enigma.recruitment.task3.device;

import com.enigma.recruitment.task3.device.model.DeviceLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceLocationRepository extends JpaRepository<DeviceLocation, Long> {
    Optional<DeviceLocation> findByDeviceIdOrderByCreatedAt(Long deviceId);
}
