package com.example.help_desk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.help_desk.entity.Asset;

import jakarta.transaction.Transactional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    Optional<Asset> findBySerialNumber(String serialNumber);
    long countByStatus(String status);
    
    @Transactional
    @Modifying
    @Query(value = "EXEC AssignAssetToUser :assetId, :userId, :adminId", nativeQuery = true)
    void assignAsset(@Param("assetId") Long assetId, 
                     @Param("userId") Long userId, 
                     @Param("adminId") Integer adminId);
    
    @Transactional
    @Modifying
    @Query(value = "EXEC ReturnAsset :assetId, :adminId", nativeQuery = true)
    void returnAsset(@Param("assetId") Long assetId, @Param("adminId") Integer adminId);
    
 // "findBy" + "AssignedTo" (the variable) + "Id" (the field inside User)
    List<Asset> findByAssignedToId(Long userId);    
}

