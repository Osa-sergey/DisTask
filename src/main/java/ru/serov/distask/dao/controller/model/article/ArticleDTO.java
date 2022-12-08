package ru.serov.distask.dao.controller.model.article;

import lombok.Data;

@Data
public class ArticleDTO {
    private Long id;
    private Long product_id;
    private String article_name;
    private String article_content;
    private String create_date;
    private String product_name;
    private String product_description;
    private Float product_implement_cost;
}
