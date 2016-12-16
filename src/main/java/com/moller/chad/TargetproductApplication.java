package com.moller.chad;

import com.moller.chad.domain.Pricing;
import com.moller.chad.domain.PricingRepository;
import com.moller.chad.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class TargetproductApplication implements CommandLineRunner {

    @Autowired
    private PricingRepository repository;

	public static void main(String[] args) {
	    SpringApplication.run(TargetproductApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        Pricing pricing = new Pricing();
        pricing.productId = 13860427;
        pricing.value = new BigDecimal("12.23");
        pricing.currency = "USD";
        repository.save(pricing);
        pricing = new Pricing();
        pricing.productId = 13860428;
        pricing.value = new BigDecimal("15.67");
        pricing.currency = "GBP";
        repository.save(pricing);
    }
}
