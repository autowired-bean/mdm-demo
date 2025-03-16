package com.example.mdmdemo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {
    private Long id;
    @NotNull private Long deviceId;
    @NotBlank private String deviceName;
    @NotNull private DeviceStatus status;
    private LocalDateTime createdAt;
}
