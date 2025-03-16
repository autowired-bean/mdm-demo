package com.example.mdmdemo.mapper;

import com.example.mdmdemo.model.Device;
import com.example.mdmdemo.model.DeviceCreationDTO;
import com.example.mdmdemo.model.DeviceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeviceMapper {

 DeviceDTO toDto(Device device);

 List<DeviceDTO> toDto(List<Device> deviceList);

 Device toDevice(DeviceDTO deviceDTO);

 Device toDevice(DeviceCreationDTO deviceDTO);


}
