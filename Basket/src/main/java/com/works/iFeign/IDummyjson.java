package com.works.iFeign;

import com.works.props.JWTData;
import com.works.props.JWTUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "dummyjson", url = "https://dummyjson.com/")
public interface IDummyjson {

    @PostMapping("auth/login")
    JWTData auth(@RequestBody JWTUser jwtUser);

}
