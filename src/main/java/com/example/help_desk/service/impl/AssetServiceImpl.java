package com.example.help_desk.service.impl;


import com.example.help_desk.entity.Asset;
import com.example.help_desk.entity.User;
import com.example.help_desk.repository.AssetRepository;
import com.example.help_desk.repository.UserRepository;
import com.example.help_desk.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;
    
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    @Transactional
    public void assignAssetToUser(Long assetId, Long userId) {
        entityManager.createNativeQuery("EXEC AssignAssetToUser :assetId, :userId")
                .setParameter("assetId", assetId)
                .setParameter("userId", userId)
                .executeUpdate();
    }
    
    @Override
    public void assignAsset(Long assetId, Long userId) {
        // Get the Admin/Technician ID from the JWT Token
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User admin = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        // Call the Stored Procedure
        assetRepository.assignAsset(assetId, userId, admin.getId());
    }
    
    @Override
    public void returnAsset(Long assetId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User admin = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        assetRepository.returnAsset(assetId, admin.getId());
    }
    
    @Override
    public List<Asset> getAssetsByUserId(Long userId) {
        // Calling the exact method we just created in the repository
        return assetRepository.findByAssignedToId(userId);
    }
    
    
}