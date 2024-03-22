package com.invoiceprocessing.server.repository;

import com.invoiceprocessing.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
