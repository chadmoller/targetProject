package com.moller.chad.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PricingRepository  extends MongoRepository<Pricing, Long>  {
}
