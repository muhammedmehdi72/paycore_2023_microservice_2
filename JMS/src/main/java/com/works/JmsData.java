package com.works;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JmsData {
    private Integer id;
    private String title;
    private String detail;
}
