package com.bootcamp.project.product.service;

import com.bootcamp.project.product.entity.ProductEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    public Flux<ProductEntity> getAll();
    public Mono<ProductEntity> getOne(String productCode);
    public Mono<ProductEntity> save(ProductEntity colEnt);
    public Mono<ProductEntity> update(String productCode, int maxOperations);
    public Mono<Void> delete(String productCode);
    public Mono<ProductEntity> findByCode(String code);
    public Flux<ProductEntity> findByType(String type);
    public Mono<ProductEntity> register(ProductEntity colEnt);
    public Mono<ProductEntity> updateBootCoinPurchaseRate(String productCode, double amount);
    public Mono<ProductEntity> updateBootCoinSaleRate(String productCode, double amount);
}
