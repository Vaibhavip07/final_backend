package com.invoiceprocessing.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer userId;

        private String userName;

        @OneToMany(mappedBy = "user" ,fetch = FetchType.EAGER)
        private List<Invoice> invoices;



}
