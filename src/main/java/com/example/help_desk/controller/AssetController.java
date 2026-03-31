package com.example.help_desk.controller;


import com.example.help_desk.dto.AssetAssignDTO;
import com.example.help_desk.entity.Asset;
import com.example.help_desk.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }
    
    @GetMapping("/user/{userId}")
    public List<Asset> getAssetsByUser(@PathVariable Long userId) {
        return assetService.getAssetsByUserId(userId);
    }
    

    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return assetService.createAsset(asset);
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignAsset(@RequestBody AssetAssignDTO request) {
        try {
            assetService.assignAsset(request.getAssetId(), request.getUserId());
            return ResponseEntity.ok(Map.of("message", "Asset assigned successfully and audit log updated."));
        } catch (RuntimeException e) {
            // This catches the 'Asset not available' error thrown by your Stored Procedure
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/{id}/return")
    public ResponseEntity<?> returnAsset(@PathVariable Long id) {
        try {
            assetService.returnAsset(id);
            return ResponseEntity.ok(Map.of("message", "Asset returned to inventory successfully."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}