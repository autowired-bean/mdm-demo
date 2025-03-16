package com.example.mdmdemo.repository;

import com.example.mdmdemo.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {


    Optional<Device> findDeviceByDeviceId(Long deviceId);

    void deleteDeviceByDeviceId(Long deviceId);


}
