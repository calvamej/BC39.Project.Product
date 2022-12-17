package com.bootcamp.project.product.service;

import com.bootcamp.project.product.entity.ProductEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    public Flux<ProductEntity> getAll();
    public Mono<ProductEntity> getOne(String productName);
    public Mono<ProductEntity> save(ProductEntity colEnt);
    public Mono<ProductEntity> update(String id, int maxOperations);
    public Mono<Void> delete(String id);
    public Mono<ProductEntity> findByCode(String code);
    public Mono<ProductEntity> findByType(String type);
    public Mono<ProductEntity> register(ProductEntity colEnt);
}
