package ru.serov.distask.dao.controller.mapper.articleentity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.repository.enity.ArticleEntity;
import ru.serov.distask.dao.repository.enity.ProductEntity;
import ru.serov.distask.model.Article;

@Mapper(componentModel = "spring")
public interface IArticleAndProductEntityToArticleMapper {

    @Mapping(target = "id", source = "article.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "articleName", source = "article.name")
    @Mapping(target = "articleContent", source = "article.content")
    @Mapping(target = "createDate", source = "article.createDate")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productDescription", source = "product.description")
    @Mapping(target = "productImplementCost", source = "product.implementCost")
    Article entityToArticle(ArticleEntity article, ProductEntity product);
}
