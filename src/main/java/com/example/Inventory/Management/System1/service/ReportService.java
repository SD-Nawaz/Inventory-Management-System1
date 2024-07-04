package com.example.Inventory.Management.System1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Inventory.Management.System1.entity.Order;
import com.example.Inventory.Management.System1.entity.Report;
import com.example.Inventory.Management.System1.entity.Stock;
import com.example.Inventory.Management.System1.entity.Supplier;
import com.example.Inventory.Management.System1.repository.OrderRepository;
import com.example.Inventory.Management.System1.repository.ReportRepository;
import com.example.Inventory.Management.System1.repository.StockRepository;
import com.example.Inventory.Management.System1.repository.SupplierRepository;

import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report generateStockReport() {
        List<Stock> stocks = stockRepository.findAll();
        StringBuilder details = new StringBuilder();
        for (Stock stock : stocks) {
            details.append("Item: ").append(stock.getName())
                    .append(", Quantity: ").append(stock.getQuantity())
                    .append(", Price: ").append(stock.getPrice()).append("\n");
        }
        Report report = new Report();
        report.setReportType("Stock Report");
        report.setGeneratedDate(new Date());
        report.setDetails(details.toString());
        return reportRepository.save(report);
    }

    public Report generateOrderReport() {
        List<Order> orders = orderRepository.findAll();
        StringBuilder details = new StringBuilder();
        for (Order order : orders) {
            details.append("Order ID: ").append(order.getId())
                    .append(", Customer: ").append(order.getCustomerName())
                    .append(", Date: ").append(order.getOrderDate())
                    .append(", Total: ").append(order.getTotalAmount()).append("\n");
        }
        Report report = new Report();
        report.setReportType("Order Report");
        report.setGeneratedDate(new Date());
        report.setDetails(details.toString());
        return reportRepository.save(report);
    }

    public Report generateSupplierReport() {
        List<Supplier> suppliers = supplierRepository.findAll();
        StringBuilder details = new StringBuilder();
        for (Supplier supplier : suppliers) {
            details.append("Supplier: ").append(supplier.getName())
                    .append(", Contact: ").append(supplier.getContactInfo()).append("\n");
        }
        Report report = new Report();
        report.setReportType("Supplier Report");
        report.setGeneratedDate(new Date());
        report.setDetails(details.toString());
        return reportRepository.save(report);
    }
}
