package ru.serov.distask.dao.controller.model.article;

import lombok.Data;

@Data
public class CArticleDTO {
    private Long product_id;
    private String name;
    private String content;
}
