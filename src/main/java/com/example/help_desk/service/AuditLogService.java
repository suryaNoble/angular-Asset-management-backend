package com.example.help_desk.service;

import com.example.help_desk.entity.AuditLogs;
import com.example.help_desk.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public List<AuditLogs> getAllLogs() {
        // Automatically sorting them by newest first is great for an Admin view!
        return auditLogRepository.findAll(Sort.by(Sort.Direction.DESC, "changedAt"));
    }
}