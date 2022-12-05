package ru.serov.distask.dao.controller.mapper.article;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.article.ArticleDTO;
import ru.serov.distask.entity.Article;

@Mapper(componentModel = "spring")
public interface IArticleDTOToProductMapper {
    @Mapping(target = "productId", source = "product_id")
    @Mapping(target = "createDate", source = "create_date")
    Article articleDTOToArticle(ArticleDTO src);

    @Mapping(target = "product_id", source = "productId")
    @Mapping(target = "create_date", source = "createDate")
    ArticleDTO articleToArticleDTO(Article src);
}
