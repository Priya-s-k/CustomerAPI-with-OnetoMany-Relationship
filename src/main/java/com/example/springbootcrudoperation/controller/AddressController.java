package com.example.springbootcrudoperation.controller;


import com.example.springbootcrudoperation.Entity.Address;
import com.example.springbootcrudoperation.Entity.Customer;
import com.example.springbootcrudoperation.repository.AddressRepository;
import com.example.springbootcrudoperation.repository.CustomerRepository;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/addr")
public class AddressController {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;


    public AddressController(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address address) {
        Optional<Customer> optionalCustomer = customerRepository.findById(address.getCustomer().getCustomerId());
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        address.setCustomer(optionalCustomer.get());

        Address savedAddress= addressRepository.save(address);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAddress.getAddressId()).toUri();

        return ResponseEntity.created(location).body(savedAddress);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@RequestBody Address address, @PathVariable Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(address.getCustomer().getCustomerId());
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        address.setCustomer(optionalCustomer.get());
        address.setAddressId(optionalAddress.get().getAddressId());
        addressRepository.save(address);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        addressRepository.delete(optionalAddress.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Address>> getAll(Pageable pageable) {
        return ResponseEntity.ok(addressRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Integer id) {
        Optional<Address> optionalAddress =addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalAddress.get());
    }

}
