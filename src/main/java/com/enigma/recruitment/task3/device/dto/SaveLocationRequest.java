package com.enigma.recruitment.task3.device.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveLocationRequest {

    @NotNull
    private Long longitude;

    @NotNull
    private Long latitude;
}
