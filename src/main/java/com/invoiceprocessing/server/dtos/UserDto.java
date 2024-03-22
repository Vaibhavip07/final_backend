package com.invoiceprocessing.server.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDto {
    private Integer userId;
    private String userName;
    private List<InvoiceDto> invoices;
}
