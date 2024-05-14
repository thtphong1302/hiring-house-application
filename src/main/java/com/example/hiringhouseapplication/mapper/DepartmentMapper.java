package com.example.hiringhouseapplication.mapper;

import com.example.hiringhouseapplication.dto.request.DepartmentRequest;
import com.example.hiringhouseapplication.dto.request.DepartmentUpdateRequest;
import com.example.hiringhouseapplication.dto.response.DepartmentResponse;
import com.example.hiringhouseapplication.entity.Department;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toDepartment(DepartmentRequest request);
    DepartmentResponse toDepartmentResponse(Department department);
    void updateDepartment(@MappingTarget Department department, DepartmentUpdateRequest request);
}
