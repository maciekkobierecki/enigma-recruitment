package com.enigma.recruitment.task3.device

import com.enigma.recruitment.task3.device.dto.SaveLocationCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
@ActiveProfiles("test")
class DeviceLocationServiceImplIT extends Specification {
    @Subject
    @Autowired
    DeviceLocationServiceImpl deviceLocationService

    def "should save deviceLocation and then find it by id"() {
        given:
        def cmd = SaveLocationCommand.builder()
                .deviceId(1L)
                .longitude(2L)
                .latitude(3L)
                .build()

        when:
        deviceLocationService.saveLocation(cmd)
        def result = deviceLocationService.getDeviceLocation(cmd.getDeviceId())

        then:
        result.id != null
        result.createdAt != null
        result.getDeviceId() == cmd.getDeviceId()
        result.getLatitude() == cmd.getLatitude()
        result.getLongitude() == cmd.getLongitude()
    }
}
