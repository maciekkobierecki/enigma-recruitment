package com.enigma.recruitment.task3.device;

import com.enigma.recruitment.task3.device.dto.SaveLocationCommand;
import com.enigma.recruitment.task3.device.model.DeviceLocation;

public interface DeviceLocationService {
    void saveLocation(SaveLocationCommand command);

    DeviceLocation getDeviceLocation(Long deviceId);
}
