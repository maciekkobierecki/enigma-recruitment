package com.enigma.recruitment.task3.device;

import com.enigma.recruitment.task3.device.dto.SaveLocationCommand;
import com.enigma.recruitment.task3.device.model.DeviceLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;

@Service
@RequiredArgsConstructor
public class DeviceLocationServiceImpl implements DeviceLocationService {

    private final DeviceLocationRepository repository;

    @Override
    public void saveLocation(SaveLocationCommand command) {
        final DeviceLocation deviceLocation = new DeviceLocation();

        deviceLocation.setDeviceId(command.getDeviceId());
        deviceLocation.setLatitude(command.getLatitude());
        deviceLocation.setLongitude(command.getLongitude());

        repository.save(deviceLocation);
    }

    @Override
    @Transactional(readOnly = true)
    public DeviceLocation getDeviceLocation(Long deviceId) {
        return repository.findByDeviceIdOrderByCreatedAt(deviceId)
                .orElseThrow(InvalidParameterException::new);
    }


}
