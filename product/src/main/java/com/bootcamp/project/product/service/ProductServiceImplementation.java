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
    public Mono<ProductEntity> getOne(String id) {
        return productRepository.findAll().filter(x -> x.getId().equals(id)).next();
    }

    @Override
    public Mono<ProductEntity> save(ProductEntity colEnt) {
        return productRepository.save(colEnt);
    }

    @Override
    public Mono<ProductEntity> update(String documentNumber, int maxOperations) {
        return getOne(documentNumber).flatMap(c -> {
            c.setMaxOperations(maxOperations);
            c.setModifyDate(new Date());
            return productRepository.save(c);
        }).switchIfEmpty(Mono.error(new CustomNotFoundException("Product not found")));
    }

    @Override
    public Mono<Void> delete(String id) {
        return getOne(id)
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Product not found")))
                .flatMap(c -> {
                    return productRepository.delete(c);
                });
    }
    @Override
    public Mono<ProductEntity> findByName(String name) {
        return productRepository.findAll().filter(x -> x.getName().equals(name)).next()
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Product not found")));
    }
    @Override
    public Mono<ProductEntity> register(ProductEntity colEnt) {
        return productRepository.findAll().filter(x -> x.getName().equals(colEnt.getName())).next()
                .switchIfEmpty(productRepository.save(colEnt));
    }
}
