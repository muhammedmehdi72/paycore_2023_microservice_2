package com.works.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Profile("test")
@Component
public class Test implements ProfileConfig{

    @Override
    public Map<String, Object> config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("rowCount", 15);
        hm.put("key", "test_key");
        hm.put("apiKey", "test_123423j4324l23klj42");
        return hm;
    }

}
