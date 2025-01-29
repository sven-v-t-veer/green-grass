package com.blueharvest.rest;

import com.blueharvest.domain.CustomerDto;
import com.blueharvest.exception.CustomerNotFoundException;
import com.blueharvest.service.CustomerService;
import com.blueharvest.spi.Customer;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@Transactional
public class CustomerRestController {

    private final CustomerService customers;
    private final TypeMap<Customer, CustomerDto> customerDataMapper;
    private final RestExceptionHandler handler;

    public CustomerRestController(CustomerService customers, TypeMap<Customer, CustomerDto> customerDataMapper, RestExceptionHandler handler) {
        this.customers = customers;
        this.customerDataMapper = customerDataMapper;
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping({"/customer/", "/customer"})
    CustomerDto createCustomer(@RequestParam(name = "user_name") String userName) {
        return customerDataMapper.map(customers.createUser(userName));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping({"/customer/{customer_id}/", "/customer/{customer_id}"})
    CustomerDto getCustomer(@PathVariable(name = "customer_id") UUID customerId) throws CustomerNotFoundException {
        return customerDataMapper.map(customers.getCustomer(customerId));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request) {
        log.warn("Handling NotFound Exception", ex);
        return handler.handleNotFound(ex, request);
    }
}
