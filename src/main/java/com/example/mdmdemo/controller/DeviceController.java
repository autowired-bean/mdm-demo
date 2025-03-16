package com.example.mdmdemo.controller;


import com.example.mdmdemo.mapper.DeviceMapper;
import com.example.mdmdemo.model.Device;
import com.example.mdmdemo.model.DeviceCreationDTO;
import com.example.mdmdemo.model.DeviceDTO;
import com.example.mdmdemo.model.DeviceStatusDTO;
import com.example.mdmdemo.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_MODIFIED;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceMapper deviceMapper;
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceMapper deviceMapper, DeviceService deviceService) {
        this.deviceMapper = deviceMapper;
        this.deviceService = deviceService;
    }

    @GetMapping("")
    public List<DeviceDTO> getAll() {
        return deviceMapper.toDto(deviceService.findAll());
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable Long deviceId) {
        Optional<Device> optDevice = deviceService.findByDeviceId(deviceId);
        return optDevice.map(device -> ResponseEntity.ok(deviceMapper.toDto(device)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("")
    public ResponseEntity<DeviceDTO> createDevice(@RequestBody @Valid DeviceCreationDTO deviceDTO) {
        System.out.println();
        return ResponseEntity.status(SC_CREATED)
                .body(deviceMapper.toDto(deviceService.saveDevice(deviceDTO)));
    }


    @PatchMapping("/{deviceId}")
    public ResponseEntity<DeviceStatusDTO> changeStatus(@PathVariable Long deviceId) {
        Optional<DeviceStatusDTO> statusDTO = deviceService.changeStatus(deviceId);
        return statusDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(SC_NOT_MODIFIED).build());
    }


    @DeleteMapping("/{deviceId}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long deviceId) {
        deviceService.deleteDevice(deviceId);
        return ResponseEntity.noContent().build();
    }

}
