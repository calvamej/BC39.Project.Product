package com.bootcamp.project.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String productCode;
    private String productName;
    private String productType;
    private double minimumOpeningBalance;
    private double maintenanceCost;
    private double minimumDailyBalance;
    private double commissionPercentage;
    private int maxMonthlyOperations;
    private double purchaseRate;
    private double saleRate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date modifyDate;



}
