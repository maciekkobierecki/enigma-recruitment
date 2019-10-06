package com.enigma.recruitment.task3.device.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class SaveLocationCommand {

    @NonNull
    private Long deviceId;

    @NonNull
    private Long longitude;

    @NonNull
    private Long latitude;
}
