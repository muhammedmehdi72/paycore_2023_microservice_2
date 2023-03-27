package com.works.restcontrollers;

import com.works.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketRestController {

    final BasketService basketService;

    @GetMapping("/allList")
    public Object allList() {
        return basketService.allList();
    }

}
