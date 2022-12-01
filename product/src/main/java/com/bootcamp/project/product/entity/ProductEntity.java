package com.bootcamp.project.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Product")
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private String productCode;
    private String productType;
    private double maintenanceCost;
    private int maxOperations;
    private Date createDate;
    private Date modifyDate;
}
