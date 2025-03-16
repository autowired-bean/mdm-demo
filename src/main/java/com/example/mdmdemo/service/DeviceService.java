package com.example.mdmdemo.service;

import com.example.mdmdemo.exception.DeviceAlreadyExistsException;
import com.example.mdmdemo.mapper.DeviceMapper;
import com.example.mdmdemo.model.Device;
import com.example.mdmdemo.model.DeviceCreationDTO;
import com.example.mdmdemo.model.DeviceStatus;
import com.example.mdmdemo.model.DeviceStatusDTO;
import com.example.mdmdemo.repository.DeviceRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;


    public DeviceService(DeviceRepository deviceRepository, DeviceMapper deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
    }

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }


    public Optional<Device> findByDeviceId(Long deviceId) {
        return deviceRepository.findDeviceByDeviceId(deviceId);
    }

    public Device saveDevice(DeviceCreationDTO deviceDto) {
        try {
            return deviceRepository.save(deviceMapper.toDevice(deviceDto));
        } catch (DataIntegrityViolationException ex) {
            throw new DeviceAlreadyExistsException("Device with deviceId=%d already exists".formatted(deviceDto.getDeviceId()));
        }
    }

    public Optional<DeviceStatusDTO> changeStatus(Long deviceId) {
        Optional<Device> optDevice = findByDeviceId(deviceId);
        if (optDevice.isPresent()) {
            var device = optDevice.get();
            DeviceStatus deviceStatus = device.getStatus();
            if (deviceStatus.equals(DeviceStatus.BLOCKED)) {
                device.setStatus(DeviceStatus.ACTIVE);
                deviceStatus = DeviceStatus.ACTIVE;
            } else {
                device.setStatus(DeviceStatus.BLOCKED);
                deviceStatus = DeviceStatus.BLOCKED;
            }

            deviceRepository.save(device);
            return Optional.of(new DeviceStatusDTO(deviceStatus));
        }

        return Optional.empty();

    }

    @Transactional
    public void deleteDevice(Long deviceId) {
        deviceRepository.deleteDeviceByDeviceId(deviceId);
    }
}
