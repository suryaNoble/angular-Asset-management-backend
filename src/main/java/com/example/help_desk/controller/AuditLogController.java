package com.example.help_desk.controller;

import com.example.help_desk.entity.AuditLogs;
import com.example.help_desk.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public List<AuditLogs> getAllLogs() {
        return auditLogService.getAllLogs();
    }
}