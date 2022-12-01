package com.bootcamp.project.product.repository;

import com.bootcamp.project.product.entity.ProductEntity;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, ObjectId> {
}
