package ru.serov.distask.dao.controller.model.articleentity;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleEntityDTO {
    private Long id;
    private Long product_id;
    private String name;
    private String content;
    private Date create_date;
}
