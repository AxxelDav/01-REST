package com.axel.project.shopping.client;

import com.axel.project.shopping.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-product")
@RequestMapping(value = "/products")
public interface ProductClient {

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

    @GetMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id, @RequestParam(name = "quantity", required = true) Double quantity);
}
