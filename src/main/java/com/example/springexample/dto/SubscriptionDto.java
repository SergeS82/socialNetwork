package com.example.springexample.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionDto {
    private Integer id;
    private Integer author;
    private Integer subscription;
}
