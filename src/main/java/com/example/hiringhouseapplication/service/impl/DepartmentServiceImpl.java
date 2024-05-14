package com.example.hiringhouseapplication.service.impl;

import com.example.hiringhouseapplication.dto.request.DepartmentRequest;
import com.example.hiringhouseapplication.dto.request.DepartmentUpdateRequest;
import com.example.hiringhouseapplication.dto.response.DepartmentResponse;
import com.example.hiringhouseapplication.entity.Department;
import com.example.hiringhouseapplication.entity.Room;
import com.example.hiringhouseapplication.exception.ApplicationError;
import com.example.hiringhouseapplication.exception.ErrorCode;
import com.example.hiringhouseapplication.mapper.DepartmentMapper;
import com.example.hiringhouseapplication.repository.DepartmentRepository;
import com.example.hiringhouseapplication.repository.RoomRepository;
import com.example.hiringhouseapplication.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final RoomRepository roomRepository;
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream().map(departmentMapper::toDepartmentResponse).toList();
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        if (departmentRepository.existsByDepartmentName(departmentRequest.getDepartmentName()))
            throw new ApplicationError(ErrorCode.RESOURCES_ALREADY_EXISTS);

        Department department = departmentMapper.toDepartment(departmentRequest);
        departmentRepository.save(department);

        return departmentMapper.toDepartmentResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartment(String departmentName, DepartmentUpdateRequest request) {
        Department department = departmentRepository.findByDepartmentName(departmentName).orElseThrow(
                () -> new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST)
        );

        departmentMapper.updateDepartment(department, request);

        departmentRepository.save(department);
        return departmentMapper.toDepartmentResponse(department);
    }

    @Override
    public void deleteDepartment(String departmentName) {
        if(!departmentRepository.existsByDepartmentName(departmentName))
            throw new ApplicationError(ErrorCode.RESOURCES_NOT_EXIST);

        departmentRepository.deleteById(departmentName);
    }


}
