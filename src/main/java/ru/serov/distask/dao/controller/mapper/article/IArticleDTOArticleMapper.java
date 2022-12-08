package ru.serov.distask.dao.controller.mapper.article;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.article.ArticleDTO;
import ru.serov.distask.model.Article;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticleDTOArticleMapper {

    List<ArticleDTO> entityToDTO(List<Article> src);

    @Mapping(target = "product_id", source = "productId")
    @Mapping(target = "article_name", source = "articleName")
    @Mapping(target = "article_content", source = "articleContent")
    @Mapping(target = "create_date", source = "createDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "product_name", source = "productName")
    @Mapping(target = "product_description", source = "productDescription")
    @Mapping(target = "product_implement_cost", source = "productImplementCost")
    ArticleDTO entityToDTO(Article src);
}
