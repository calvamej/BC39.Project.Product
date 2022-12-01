package com.bootcamp.project.product.service;

import com.bootcamp.project.product.entity.ProductEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    public Flux<ProductEntity> getAll();
    public Mono<ProductEntity> getOne(String id);

    public Mono<ProductEntity> save(ProductEntity colEnt);
    public Mono<ProductEntity> update(String id, int maxOperations);
    public Mono<Void> delete(String id);
    public Mono<ProductEntity> findByName(String name);
    public Mono<ProductEntity> register(ProductEntity colEnt);
}
