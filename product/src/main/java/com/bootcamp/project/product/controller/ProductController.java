package com.bootcamp.project.product.controller;

import com.bootcamp.project.product.entity.ProductEntity;
import com.bootcamp.project.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/Product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/FindOne/{productCode}")
    public Mono<ProductEntity> Get_One(@PathVariable("productCode") String productCode){
        return productService.getOne(productCode);
    }
    @GetMapping(value = "/FindAll")
    public Flux<ProductEntity> Get_All(){

        return productService.getAll();
    }
    @PostMapping(value = "/Save")
    public Mono<ProductEntity> Save(@RequestBody ProductEntity col){

        return productService.save(col);
    }
    @PutMapping(value = "/Update/{productCode}/{maxOperations}")
    public Mono<ProductEntity> Update(@PathVariable("productCode") String productCode,@PathVariable("maxOperations") int maxOperations){
        return productService.update(productCode, maxOperations);
    }
    @DeleteMapping  (value = "/Delete/{productCode}")
    public Mono<Void> Delete(@PathVariable("productCode") String productCode){
        return productService.delete(productCode);
    }
    @GetMapping(value = "/FindByCode/{code}")
    public Mono<ProductEntity> findByCode(@PathVariable("code") String code){
        return productService.findByCode(code);
    }
    @GetMapping(value = "/FindByType/{type}")
    public Flux<ProductEntity> findByType(@PathVariable("type") String type){
        return productService.findByType(type);
    }
    @PostMapping(value = "/Register")
    public Mono<ProductEntity> registerClient(@RequestBody ProductEntity col){
        return productService.register(col);
    }
    @PutMapping(value = "/UpdateBootCoinPurchaseRate/{productCode}/{amount}")
    public Mono<ProductEntity> updateBootCoinPurchaseRate(@PathVariable("productCode") String productCode,@PathVariable("amount") double amount){
        return productService.updateBootCoinPurchaseRate(productCode, amount);
    }
    @PutMapping(value = "/UpdateBootCoinSaleRate/{productCode}/{amount}")
    public Mono<ProductEntity> updateBootCoinSaleRate(@PathVariable("productCode") String productCode,@PathVariable("amount") double amount){
        return productService.updateBootCoinSaleRate(productCode, amount);
    }
}
