package com.works.services;

import com.works.props.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    final DiscoveryClient discoveryClient;
    final RestTemplate restTemplate;

    public Object allList() {
        List<ServiceInstance> list = discoveryClient.getInstances("product");
        if ( list != null && list.size() > 0 ) {
            String baseUrl = list.get(0).getUri().toString();
            baseUrl = baseUrl + "/product/list";
            Product[] stData = restTemplate.getForObject(baseUrl, Product[].class);
            return stData;
        }
        return null;
    }

}
