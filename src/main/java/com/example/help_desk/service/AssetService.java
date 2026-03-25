package com.example.help_desk.service;


import java.util.List;

import com.example.help_desk.entity.Asset;

public interface AssetService {
    List<Asset> getAllAssets();
    Asset createAsset(Asset asset);
    void assignAssetToUser(Long assetId, Long userId);
}