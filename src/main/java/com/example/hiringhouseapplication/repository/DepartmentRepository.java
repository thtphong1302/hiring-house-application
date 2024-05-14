package com.example.hiringhouseapplication.repository;

import com.example.hiringhouseapplication.entity.Department;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    boolean existsByDepartmentName(String departmentName);
    Optional<Department> findByDepartmentName(String departmentName);
}
