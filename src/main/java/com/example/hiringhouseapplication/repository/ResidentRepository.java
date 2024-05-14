package com.example.hiringhouseapplication.repository;

import com.example.hiringhouseapplication.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, String> {
    Optional<Resident> findByIdentityNumber(String identityNumber);

    boolean existsByIdentityNumber(String identityNumber);
}
