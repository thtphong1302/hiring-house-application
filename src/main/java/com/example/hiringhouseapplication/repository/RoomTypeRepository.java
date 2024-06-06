package com.example.hiringhouseapplication.repository;

import com.example.hiringhouseapplication.dto.response.RoomTypeResponse;
import com.example.hiringhouseapplication.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, String> {
    boolean existsByTypeName(String typeName);
    Optional<RoomType> findByTypeName(String typeName);

}
