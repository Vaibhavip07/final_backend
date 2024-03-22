package com.invoiceprocessing.server.repository;

import com.invoiceprocessing.server.model.Invoice;
import com.invoiceprocessing.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice,Integer> {
    List<Invoice> findInvoiceByUser(User user);

    void deleteInvoiceById(Integer id);
}
