package ru.serov.distask.dao.controller.model.article;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDTO {
    private Long id;
    private Long product_id;
    private String name;
    private String content;
    private Date create_date;
}
