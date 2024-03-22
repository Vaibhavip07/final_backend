package com.invoiceprocessing.server.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class InvoiceDto {

    private long id;
    private String vendor;
    private String product;
    private int amount;
    private String date;
    private String action;

}
