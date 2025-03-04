package com.ineos.management.dao;


import com.ineos.management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

     Customer findByEmail(String _email);
}
