package com.example.hiringhouseapplication.repository;

import com.example.hiringhouseapplication.entity.Resident;
import com.example.hiringhouseapplication.entity.Room;
import com.example.hiringhouseapplication.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,String> {
    Optional<Room> findByRoomName(String roomName);
    @Query("SELECT r FROM Room r WHERE r.department.departmentName = ?1")
    List<Room> getAllRoomsInDepartment(String departmentName);
    @Query("SELECT r FROM Room  r WHERE r.department.departmentName = ?1 and r.status = ?2")
    List<Room> getStatusRoomsInDepartment(String departmentName, Status status);

    boolean existsByRoomName(String roomName);

    @Transactional
    @Modifying
    @Query("UPDATE Room set status = :status WHERE roomName = :roomName")
    void updateStatus(@Param("roomName") String roomName,@Param("status") Status status);

    @Query("SELECT rs FROM Resident rs " +
            "INNER JOIN Room r on rs.room.roomName = r.roomName " +
            "WHERE r.roomName = ?1")
    List<Resident> getAllResidentsInRoom(String roomName);
}
