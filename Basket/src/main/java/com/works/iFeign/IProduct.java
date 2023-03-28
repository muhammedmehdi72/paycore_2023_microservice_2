package com.works.iFeign;

import com.works.props.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "product")
public interface IProduct {

    @GetMapping("/product/list")
    Product[] proList();

    @PostMapping("/product/save")
    Product proSave( @RequestBody Product product );

}
