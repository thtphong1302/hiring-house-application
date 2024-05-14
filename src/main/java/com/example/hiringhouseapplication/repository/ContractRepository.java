package com.example.hiringhouseapplication.repository;

import com.example.hiringhouseapplication.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {
    Optional<Contract> findByContractID(String contractID);
    boolean existsByContractID(String contractID);
}
