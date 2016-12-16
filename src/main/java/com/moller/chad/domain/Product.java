package com.moller.chad.domain;

import org.springframework.data.annotation.Id;

public class Product {
    @Id
    public long id;
    public String content;
    public Pricing currentPrice;
}
