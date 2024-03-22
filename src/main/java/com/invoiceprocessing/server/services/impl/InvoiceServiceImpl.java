package com.invoiceprocessing.server.services.impl;

import com.invoiceprocessing.server.dao.InvoiceDao;
import com.invoiceprocessing.server.dtos.InvoiceDto;
import com.invoiceprocessing.server.exception.ResourceNotFound;
import com.invoiceprocessing.server.model.Invoice;
import com.invoiceprocessing.server.model.User;
import com.invoiceprocessing.server.repository.InvoiceRepo;
import com.invoiceprocessing.server.repository.UserRepo;
import com.invoiceprocessing.server.services.InvoiceService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private ModelMapper model;

    @Autowired
    private UserRepo userRepo;

    @Override
    public InvoiceDto createInvoice(InvoiceDto invoiceDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "Id", userId));
        Invoice invoice1 = model.map(invoiceDto, Invoice.class);
        invoice1.setUser(user);
        Invoice i1 = invoiceRepo.save(invoice1);
        return model.map(i1, InvoiceDto.class);
    }

    @Override
    public InvoiceDto updateInvoice(InvoiceDto invoiceDto, Integer id) {
        Invoice i1 = invoiceRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Player", "Id", id));
        //i1.setId(invoiceDto.getId());
        i1.setVendor(invoiceDto.getVendor());
        i1.setProduct(invoiceDto.getProduct());
        i1.setAmount(invoiceDto.getAmount());
        i1.setDate(invoiceDto.getDate());
        //i1.setAction(invoiceDto.getAction());


        Invoice i2 = invoiceRepo.save(i1);
        return model.map(i2, InvoiceDto.class);
    }

    @Override
    public InvoiceDto getInvoiceById(Integer id) {
        Invoice invoice = invoiceRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Player", "Id", id));
        return model.map(invoice,InvoiceDto.class);
    }

    @Override
    public List<InvoiceDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepo.findAll();
        List<InvoiceDto> invoiceDtos = invoices.stream().map(player -> model.map(player,InvoiceDto.class)).collect(Collectors.toList());
        return invoiceDtos;
    }

    @Override
    public List<InvoiceDto> getAllInvoiceByUserId(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("Invoice", "id", userId));
        List<Invoice> invoices = invoiceRepo.findInvoiceByUser(user);
        List<InvoiceDto> invoiceDtos = invoices.stream().map(player -> model.map(player, InvoiceDto.class)).collect(Collectors.toList());
        return invoiceDtos;
    }

    @Override
    public boolean deleteInvoiceById(Integer id) {

        Invoice invoiceOptional = invoiceRepo.findById(id).orElseThrow();

        invoiceRepo.deleteById((int) invoiceOptional.getId());
        return true;
    }
}
