package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@Transactional
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Customer> createOrUpdateCustomer(@RequestBody Customer customer) {
        Optional<Customer> returnedCustomer = Objects.nonNull(customer.getUserId()) ?
                customerRepository.findById(customer.getUserId()) :
                Optional.empty();
        Customer savedCustomer = returnedCustomer.orElse(customer);
        if (!savedCustomer.equals(customer)) {
            savedCustomer.setFirstName(customer.getFirstName());
            savedCustomer.setLastName(customer.getLastName());
            savedCustomer.getUser().setCustomer(savedCustomer);
            savedCustomer.getUser().setId(savedCustomer.getUserId());
        }
        if (Objects.isNull(savedCustomer.getUser()))
            savedCustomer.setUser(new User(savedCustomer.getFirstName() + savedCustomer.getLastName(),
                    "BTQ@2o2!@", User.Roles.CUSTOMER, savedCustomer));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerRepository.save(savedCustomer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable UUID id) {
        return ResponseEntity.ok(customerRepository.findById(id).orElse(new Customer()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        boolean flag = customerRepository.findById(id).isPresent();
        customerRepository.deleteById(id);
        return flag ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }
}
