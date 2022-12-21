package com.bootcamp.project.product.service;

import com.bootcamp.project.product.entity.ProductEntity;
import com.bootcamp.project.product.exception.CustomNotFoundException;
import com.bootcamp.project.product.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ProductServiceImplementation implements ProductService{
    private static Logger Log = Logger.getLogger(ProductServiceImplementation.class);
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Flux<ProductEntity> getAll() {
        return productRepository.findAll().switchIfEmpty(Mono.error(new CustomNotFoundException("Products not found")));
    }
    @Override
    public Mono<ProductEntity> getOne(String productCode) {
        return productRepository.findAll().filter(x -> x.getProductCode() != null && x.getProductCode().equals(productCode)).next();
    }
    @Override
    public Mono<ProductEntity> save(ProductEntity colEnt) {
        colEnt.setCreateDate(new Date());
        return productRepository.save(colEnt);
    }

    @Override
    public Mono<ProductEntity> update(String productCode, int maxOperations) {
        return getOne(productCode).flatMap(c -> {
            c.setMaxMonthlyOperations(maxOperations);
            c.setModifyDate(new Date());
            return productRepository.save(c);
        }).switchIfEmpty(Mono.error(new CustomNotFoundException("Product not found")));
    }

    @Override
    public Mono<Void> delete(String productCode) {
        return getOne(productCode)
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Product not found")))
                .flatMap(c -> {
                    return productRepository.delete(c);
                });
    }
    @Override
    public Mono<ProductEntity> findByCode(String code) {
        return productRepository.findAll().filter(x -> x.getProductCode() != null && x.getProductCode().equals(code)).next()
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Product not found")));
    }
    @Override
    public Flux<ProductEntity> findByType(String type) {
        return productRepository.findAll().filter(x -> x.getProductType() != null && x.getProductType().equals(type))
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Product not found")));
    }
    @Override
    public Mono<ProductEntity> register(ProductEntity colEnt) {
        colEnt.setCreateDate(new Date());
        return productRepository.findAll().filter(x -> x.getProductCode() != null && x.getProductCode().equals(colEnt.getProductCode())).next()
                .switchIfEmpty(productRepository.save(colEnt));
    }
    @Override
    public Mono<ProductEntity> updateBootCoinPurchaseRate(String productCode, double amount) {
        return getOne(productCode).flatMap(c -> {
            c.setPurchaseRate(amount);
            c.setModifyDate(new Date());
            return productRepository.save(c);
        }).switchIfEmpty(Mono.error(new CustomNotFoundException("Product not found")));
    }
    @Override
    public Mono<ProductEntity> updateBootCoinSaleRate(String productCode, double amount) {
        return getOne(productCode).flatMap(c -> {
            c.setSaleRate(amount);
            c.setModifyDate(new Date());
            return productRepository.save(c);
        }).switchIfEmpty(Mono.error(new CustomNotFoundException("Product not found")));
    }
}
