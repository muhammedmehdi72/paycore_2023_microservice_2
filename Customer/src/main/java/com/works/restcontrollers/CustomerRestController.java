package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerRestController {

    final CustomerService customerService;

    @PostMapping("/register")
    public Customer register( @RequestBody Customer customer ) {
        return customerService.register(customer);
    }

    @PostMapping("/status")
    public Boolean status() {
        return true;
    }

}
