package com.invoiceprocessing.server.services;

import com.invoiceprocessing.server.dtos.InvoiceDto;
import com.invoiceprocessing.server.model.Invoice;

import java.util.List;

public interface InvoiceService {

    InvoiceDto createInvoice(InvoiceDto invoiceDto, Integer userId);
    InvoiceDto updateInvoice(InvoiceDto invoiceDto,  Integer id);
    InvoiceDto getInvoiceById(Integer id);
    List<InvoiceDto> getAllInvoices();
    List<InvoiceDto> getAllInvoiceByUserId(Integer userId);

    //void deleteInvoiceById(Integer id);
    boolean deleteInvoiceById(Integer id);
}
