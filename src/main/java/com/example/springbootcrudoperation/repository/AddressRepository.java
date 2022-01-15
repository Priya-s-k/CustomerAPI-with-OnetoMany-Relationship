package com.example.springbootcrudoperation.repository;

import com.example.springbootcrudoperation.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Integer> {
}
