package com.gold.smith.invoice.controller;

import com.gold.smith.exception.ResourceNotFoundException;
import com.gold.smith.invoice.model.Invoice;
import com.gold.smith.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {
    @Autowired
    InvoiceRepository invoiceRepository;


    @GetMapping("/invoices")
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @PostMapping("/invoice")
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @GetMapping("/invoice/{id}")
    public Invoice getInvoiceById(@PathVariable(value = "id") Long invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", invoiceId));
    }

    @PutMapping("/invoice/{id}")
    public Invoice updateInvoice(@PathVariable(value = "id") Long invoiceId, @RequestBody Invoice invoiceDetails) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", invoiceId));
        invoice.setDeliveryDate(invoiceDetails.getDeliveryDate());
        invoice.setGold12Per(invoiceDetails.getGold12Per());
        invoice.setGold92Per(invoiceDetails.getGold92Per());
        invoice.setGrossWeight(invoiceDetails.getGrossWeight());
        invoice.setItemName(invoiceDetails.getItemName());
        invoice.setMakingCharges(invoiceDetails.getMakingCharges());
        invoice.setShowroom(invoiceDetails.getShowroom());
        invoice.setNetWeight(invoiceDetails.getNetWeight());
        invoice.setOrderNumber(invoiceDetails.getOrderNumber());
        invoice.setTotalStones(invoiceDetails.getTotalStones());
        invoice.setStoneWeight(invoiceDetails.getStoneWeight());
        return invoiceRepository.save(invoice);
    }

    @DeleteMapping("/invoice/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable(value = "id") Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", invoiceId));
        invoiceRepository.delete(invoice);
        return ResponseEntity.ok().build();
    }
}
