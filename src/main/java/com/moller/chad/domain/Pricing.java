package com.moller.chad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;


public class Pricing {
    @JsonIgnore
    @Id
    public long productId;
    public BigDecimal value;
    public String currency;
}
