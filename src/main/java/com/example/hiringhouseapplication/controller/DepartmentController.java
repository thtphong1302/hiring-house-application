package com.example.hiringhouseapplication.controller;

import com.example.hiringhouseapplication.dto.request.DepartmentRequest;
import com.example.hiringhouseapplication.dto.request.DepartmentUpdateRequest;
import com.example.hiringhouseapplication.dto.response.ApiResponse;
import com.example.hiringhouseapplication.dto.response.DepartmentResponse;
import com.example.hiringhouseapplication.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ApiResponse<List<DepartmentResponse>> getAllDepartments() {
        var result = departmentService.getAllDepartments();
        return ApiResponse.<List<DepartmentResponse>>builder().result(result).build();
    }

    @PostMapping
    public ApiResponse<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        var result = departmentService.createDepartment(departmentRequest);
        return ApiResponse.<DepartmentResponse>builder().result(result).build();
    }
    @PutMapping("/{departmentName}")
    public ApiResponse<DepartmentResponse> updateDepartment(
            @PathVariable("departmentName") String departmentName, @RequestBody DepartmentUpdateRequest request){
        var result = departmentService.updateDepartment(departmentName, request);
        return ApiResponse.<DepartmentResponse>builder().result(result).build();
    }
    @DeleteMapping("/{departmentName}")
    public ApiResponse<Void> deleteDepartment(@PathVariable("departmentName") String departmentName) {
        departmentService.deleteDepartment(departmentName);
        return ApiResponse.<Void>builder().message("Delete department successfully").build();
    }
}
