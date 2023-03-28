package com.works.restcontrollers;

import com.works.iFeign.IDummyjson;
import com.works.props.JWTData;
import com.works.props.JWTUser;
import com.works.props.Product;
import com.works.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketRestController {

    final BasketService basketService;
    final IDummyjson iDummyjson;

    @GetMapping("/allList")
    public Object allList() {
        return basketService.allList();
    }

    @GetMapping("/proSave")
    public Product proSave(){
        return basketService.proSave();
    }

    @PostMapping("/dummyAuth")
    public JWTData dummyAuth(@RequestBody JWTUser jwtUser) {
        try {
            return iDummyjson.auth(jwtUser);
        }catch (Exception ex) {
            return null;
        }
    }

}
