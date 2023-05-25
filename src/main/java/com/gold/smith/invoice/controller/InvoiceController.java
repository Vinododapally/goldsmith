package com.gold.smith.invoice.controller;

import com.gold.smith.exception.ResourceNotFoundException;
import com.gold.smith.invoice.model.Invoice;
import com.gold.smith.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080/goldsmith-frontend")
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
    public Invoice updateInvoice(@PathVariable(value = "id") Long invoiceId, @RequestBody Invoice invoiceDetails) throws ParseException {
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
        invoice.setStoneWeightInCarat(invoiceDetails.getStoneWeightInCarat());
        invoice.setStoneWeightInGrams(invoiceDetails.getStoneWeightInGrams());
        return invoiceRepository.save(invoice);
    }

    @DeleteMapping("/invoice/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable(value = "id") Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", invoiceId));
        invoiceRepository.delete(invoice);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/invoice/filters")
    public List<Invoice> getInvoicesBasedOnParam(@RequestBody Invoice invoice) throws ParseException {
        String showroom = invoice.getShowroom();
        Date fromDate = null;
        Date toDate = null;
        if (invoice.getDeliveryDate() != null) {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat(invoice.getDeliveryDate()));
        }
        if (invoice.getToDate() != null) {
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat(invoice.getToDate()));
        }
        if ((showroom != null && toDate != null && fromDate == null) || (showroom != null && fromDate != null && toDate == null)) {
            if (fromDate != null) {
                toDate = fromDate;
            }
            return invoiceRepository.getInvoicesBasedOnTheParams(showroom, toDate);
        }
        return invoiceRepository.getInvoicesBasedOnTheParam(showroom, fromDate, toDate);

    }

    private String dateFormat(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy");
        Date parsedDate = sdf.parse(String.valueOf(date));
        SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd");
        return print.format(parsedDate);
    }
}
