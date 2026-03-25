package com.example.help_desk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.help_desk.entity.AuditLogs;

public interface AuditLogRepository extends JpaRepository<AuditLogs, Integer> {
}