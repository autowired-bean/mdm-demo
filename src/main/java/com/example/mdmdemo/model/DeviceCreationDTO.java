package com.example.mdmdemo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceCreationDTO {
    @NotNull private Long deviceId;
    @NotBlank private String deviceName;
    @NotNull private DeviceStatus status;
}
