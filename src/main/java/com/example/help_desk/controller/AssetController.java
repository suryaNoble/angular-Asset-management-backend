package com.example.help_desk.controller;


import com.example.help_desk.dto.AssetAssignDTO;
import com.example.help_desk.entity.Asset;
import com.example.help_desk.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return assetService.createAsset(asset);
    }

    @PostMapping("/assign")
    public String assignAsset(@RequestBody AssetAssignDTO dto) {
        assetService.assignAssetToUser(dto.getAssetId(), dto.getUserId());
        return "Asset assigned successfully";
    }
}