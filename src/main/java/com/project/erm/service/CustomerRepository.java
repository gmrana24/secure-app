package com.project.erm.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.erm.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Optional<Customer> findByEmail(String email);
}
