package com.example.hiringhouseapplication.service;


import com.example.hiringhouseapplication.dto.request.DepartmentRequest;
import com.example.hiringhouseapplication.dto.request.DepartmentUpdateRequest;
import com.example.hiringhouseapplication.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> getAllDepartments();
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
    DepartmentResponse updateDepartment(String departmentName, DepartmentUpdateRequest request);
    void deleteDepartment(String departmentName);
}
