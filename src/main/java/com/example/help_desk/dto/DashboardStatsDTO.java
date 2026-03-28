package com.example.help_desk.dto;

public class DashboardStatsDTO {
	private long openTickets;
    private long assignedAssets;
    private long availableAssets;
    private long urgentTickets; // Pending > 24 hours

    // Constructor, Getters, and Setters
    public DashboardStatsDTO(long openTickets, long assignedAssets, long availableAssets, long urgentTickets) {
    	this.openTickets = openTickets;
        this.assignedAssets = assignedAssets;
        this.availableAssets = availableAssets;
        this.urgentTickets = urgentTickets;
    }
        public long getOpenTickets() { return openTickets; }
        public long getAssignedAssets() { return assignedAssets; }
        public long getAvailableAssets() { return availableAssets; }
        public long getUrgentTickets() { return urgentTickets; }
}