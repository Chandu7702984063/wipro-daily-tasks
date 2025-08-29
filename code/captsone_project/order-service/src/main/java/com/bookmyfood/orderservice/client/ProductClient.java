package com.bookmyfood.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @PostMapping("/product/{id}/reserve")
    void reserve(@PathVariable("id") Long productId, @RequestParam("qty") int qty);

    @PostMapping("/product/{id}/release")
    void release(@PathVariable("id") Long productId, @RequestParam("qty") int qty);
}
