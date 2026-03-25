package com.example.help_desk.dto;


public class TicketRequestDTO {

    private String title;
    private String description;
    private Long assetId;
    private Long createdBy;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getAssetId() {
        return assetId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }
}