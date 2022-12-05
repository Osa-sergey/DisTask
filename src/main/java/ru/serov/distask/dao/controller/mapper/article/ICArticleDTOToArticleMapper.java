package ru.serov.distask.dao.controller.mapper.article;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.article.CArticleDTO;
import ru.serov.distask.entity.Article;

@Mapper(componentModel = "spring")
public interface ICArticleDTOToArticleMapper {
    @Mapping(target = "productId", source = "product_id")
    Article cArticleDTOToArticle(CArticleDTO src);
}
