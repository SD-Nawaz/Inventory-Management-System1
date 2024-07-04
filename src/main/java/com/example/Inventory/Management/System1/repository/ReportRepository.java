package com.example.Inventory.Management.System1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Inventory.Management.System1.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
