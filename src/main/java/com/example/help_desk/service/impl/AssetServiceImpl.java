package com.example.help_desk.service.impl;


import com.example.help_desk.entity.Asset;
import com.example.help_desk.repository.AssetRepository;
import com.example.help_desk.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

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
}