package com.invoiceprocessing.server.controller;

import com.invoiceprocessing.server.dtos.InvoiceDto;
import com.invoiceprocessing.server.model.Invoice;
import com.invoiceprocessing.server.repository.InvoiceRepo;
import com.invoiceprocessing.server.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;
    @Autowired
    InvoiceRepo invoiceRepo;



    @PostMapping("/user/{userId}/")
    private ResponseEntity<?> createInvoice(@RequestBody InvoiceDto invoice, @PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(invoiceService.createInvoice(invoice, userId), HttpStatus.CREATED);
    }



    @PutMapping("/user/invoice/{id}")
    private ResponseEntity<?> updateInvoice(@RequestBody InvoiceDto invoice ,@PathVariable("id") Integer id) {
        InvoiceDto updatedInvoice = invoiceService.updateInvoice(invoice,  id);
        if (updatedInvoice != null) {
            return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user/invoice/{id}")
    private ResponseEntity<?> getInvoiceById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(invoiceService.getInvoiceById(id), HttpStatus.OK);
    }



    @GetMapping("/invoice/")
    private ResponseEntity<?> getAllInvoices() {
        return new ResponseEntity<>(invoiceService.getAllInvoices(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/invoice/")
    private ResponseEntity<?> getInvoiceByUserId(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(invoiceService.getAllInvoiceByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping("/invoice/{id}")
    private ResponseEntity<String> deleteInvoiceById(@PathVariable("id") Integer id) {
        boolean deletionResult = invoiceService.deleteInvoiceById(id);
        if (deletionResult) {
            return ResponseEntity.ok().body("Invoice with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete invoice with ID " + id);
        }
    }





}
