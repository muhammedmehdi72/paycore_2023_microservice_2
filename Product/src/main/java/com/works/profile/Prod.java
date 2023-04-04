package com.works.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Profile("prod")
@Component
public class Prod implements ProfileConfig{

    @Override
    public Map<String, Object> config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("rowCount", 30);
        hm.put("key", "prod_key");
        hm.put("apiKey", "prod_123423j4324l23klj42");
        return hm;
    }

}