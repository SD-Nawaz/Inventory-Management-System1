package com.example.Inventory.Management.System1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Inventory.Management.System1.entity.Report;
import com.example.Inventory.Management.System1.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/stock")
    public Report generateStockReport() {
        return reportService.generateStockReport();
    }

    @GetMapping("/order")
    public Report generateOrderReport() {
        return reportService.generateOrderReport();
    }

    @GetMapping("/supplier")
    public Report generateSupplierReport() {
        return reportService.generateSupplierReport();
    }
}
