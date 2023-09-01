package com.example.demo_jwt.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseParam {
    private String key;
    private String value;
}
