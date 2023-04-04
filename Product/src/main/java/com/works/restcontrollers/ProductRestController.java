package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.profile.ProfileConfig;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {

    final ProductService productService;
    final ProfileConfig profileConfig;

    @PostMapping("/save")
    public Product save( @RequestBody Product product ) {
        return productService.save(product);
    }

    @GetMapping("/list")
    public List<Product> list() {
        return productService.list();
    }

    @GetMapping("/profile")
    public Map config() {
        return profileConfig.config();
    }

}
