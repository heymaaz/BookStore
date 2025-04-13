package com.maazchowdhry.bookstore.catalog.web.controllers;

import com.maazchowdhry.bookstore.catalog.domain.PagedResult;
import com.maazchowdhry.bookstore.catalog.domain.Product;
import com.maazchowdhry.bookstore.catalog.domain.ProductNotFoundException;
import com.maazchowdhry.bookstore.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        return productService
                .findByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }

    @GetMapping("/secrets")
    ResponseEntity<String> shareSecrets() {
        return ResponseEntity.ok().body("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");
    }
}
