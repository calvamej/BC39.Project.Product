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

    @GetMapping(value = "/FindOne/{id}")
    public Mono<ProductEntity> Get_One(@PathVariable("id") String id){
        return productService.getOne(id);
    }
    @GetMapping(value = "/FindAll")
    public Flux<ProductEntity> Get_All(){

        return productService.getAll();
    }
    @PostMapping(value = "/Save")
    public Mono<ProductEntity> Save(@RequestBody ProductEntity col){

        return productService.save(col);
    }
    @PutMapping(value = "/Update/{id}/{maxOperations}")
    public Mono<ProductEntity> Update(@PathVariable("id") String id,@PathVariable("maxOperations") int maxOperations){
        return productService.update(id, maxOperations);
    }
    @DeleteMapping  (value = "/Delete/{id}")
    public Mono<Void> Delete(@PathVariable("id") String id){
        return productService.delete(id);
    }
    @GetMapping(value = "/FindByName/{name}")
    public Mono<ProductEntity> findClientByDocument(@PathVariable("name") String name){
        return productService.findByName(name);
    }
    @PostMapping(value = "/Register")
    public Mono<ProductEntity> registerClient(@RequestBody ProductEntity col){
        return productService.register(col);
    }
}
