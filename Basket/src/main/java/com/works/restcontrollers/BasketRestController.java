package com.works.restcontrollers;

import com.google.gson.Gson;
import com.works.iFeign.IDummyjson;
import com.works.props.JWTData;
import com.works.props.JWTUser;
import com.works.props.JmsData;
import com.works.props.Product;
import com.works.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketRestController {

    final BasketService basketService;
    final IDummyjson iDummyjson;
    final JmsTemplate jmsTemplate;
    final Gson gson;

    @GetMapping("/allList")
    public Object allList() {
        return basketService.allList();
    }

    @GetMapping("/proSave")
    public Product proSave(@RequestHeader Map map){
        String token = map.get("authorization").toString();
        System.out.println(token);
        Map hm = new HashMap();
        hm.put("Authorization", token);
        return basketService.proSave(hm);
    }

    @PostMapping("/dummyAuth")
    public JWTData dummyAuth(@RequestBody JWTUser jwtUser) {
        try {
            return iDummyjson.auth(jwtUser);
        }catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/jmsSend")
    public String jmsSend() {
        long start = System.currentTimeMillis();
        System.out.println("Start : " + start);
        for (int i = 0; i < 10000; i++) {
            JmsData data = new JmsData(i, "Msg Title -" +i , "Msg Detail");
            String stJmsData = gson.toJson(data);
            jmsTemplate.convertAndSend(stJmsData);
        }

        return "send..";
    }

}
