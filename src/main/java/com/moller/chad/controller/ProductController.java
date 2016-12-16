package com.moller.chad.controller;

import com.moller.chad.domain.Pricing;
import com.moller.chad.domain.PricingRepository;
import com.moller.chad.domain.Product;
import com.moller.chad.service.ProductNameService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private Log log = LogFactory.getLog(ProductController.class);
    @Autowired
    PricingRepository pricingRepository;
    @Autowired
    ProductNameService productNameService;

    @RequestMapping(value = "/product/{pid}", method = RequestMethod.GET)
    public Product product(@PathVariable long pid) {
        String name = productNameService.getName(pid);

        Product product = new Product();
        product.id = pid;
        product.content = name;
        Pricing pricing = pricingRepository.findOne(pid);
        product.currentPrice =pricing;
        return product;
    }

}