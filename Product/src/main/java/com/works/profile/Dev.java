package com.works.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Profile("dev")
@Component
public class Dev implements ProfileConfig{

    @Override
    public Map<String, Object> config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("rowCount", 50);
        hm.put("key", "dev_key");
        hm.put("apiKey", "dev_123423j4324l23klj42");
        return hm;
    }

}
