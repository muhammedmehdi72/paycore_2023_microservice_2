package com.works.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.works.iFeign.IProduct;
import com.works.props.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BasketService {

    final DiscoveryClient discoveryClient;
    final RestTemplate restTemplate;
    final IProduct iProduct;

    @HystrixCommand(
            fallbackMethod = "backFnc",
            commandProperties = { @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")},
            ignoreExceptions = { ArithmeticException.class, SQLException.class }
    )
    public Object allList() {
        /*
        List<ServiceInstance> list = discoveryClient.getInstances("product");
        if ( list != null && list.size() > 0 ) {
            String baseUrl = list.get(0).getUri().toString();
            baseUrl = baseUrl + "/product/list";
            Product[] stData = restTemplate.getForObject(baseUrl, Product[].class);
        }
        */
        //int i = 1 / 0;
        try {
            //Thread.sleep(1100);
        }catch (Exception ex) {}
        long start = System.currentTimeMillis();
        Product[] products = iProduct.proList();
        long end = System.currentTimeMillis();
        long between = end - start;
        System.out.println( between );

        return products;
    }

    @HystrixCommand(fallbackMethod = "backFncBackUp")
    public Object backFnc() {
        Map map = new LinkedHashMap();
        map.put("status", false);
        map.put("message", "Try Again");
        return map;
    }

    public Object backFncBackUp() {
        Map map = new LinkedHashMap();
        map.put("status", false);
        map.put("message", "Try Again");
        return map;
    }

    public Product proSave( ) {
        Product product = new Product();
        product.setPrice( new Random(10000).nextInt() );
        product.setTitle( UUID.randomUUID().toString() );
        return iProduct.proSave(product);
    }

}
