package ru.serov.distask.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Article {
    private Long id;
    private Long productId;
    private String articleName;
    private String articleContent;
    private LocalDate createDate;
    private String productName;
    private String productDescription;
    private Float productImplementCost;
}
