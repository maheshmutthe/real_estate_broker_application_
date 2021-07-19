package com.reba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reba.entities.Customer;

@Repository("customerRepo")
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
