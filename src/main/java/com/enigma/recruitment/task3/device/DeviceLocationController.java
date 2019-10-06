package com.enigma.recruitment.task3.device;

import com.enigma.recruitment.task3.device.dto.SaveLocationCommand;
import com.enigma.recruitment.task3.device.dto.SaveLocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeviceLocationController {

    private final DeviceLocationService deviceLocationService;

    @PostMapping("/api/device/{deviceId}/locations")
    public void saveLocation(@PathVariable("deviceId") Long deviceId, @RequestBody SaveLocationRequest request) {
        deviceLocationService.saveLocation(
                SaveLocationCommand.builder()
                        .deviceId(deviceId)
                        .latitude(request.getLatitude())
                        .longitude(request.getLongitude())
                        .build()
        );
    }
}
