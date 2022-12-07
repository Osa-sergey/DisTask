package ru.serov.distask.dao.controller.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.serov.distask.dao.controller.model.product.ProductDTO;
import ru.serov.distask.dao.repository.enity.ArticleEntity;
import ru.serov.distask.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IProductDTOProductMapper {
    @Named("convertArticles")
    static List<Long> convertArticles(List<ArticleEntity> articles) {
        return articles.stream().map(ArticleEntity::getId).collect(Collectors.toList());
    }

    @Mapping(target = "implement_cost", source = "implementCost")
    @Mapping(target = "article_ids", source = "articles", qualifiedByName = "convertArticles")
    ProductDTO entityToDTO(Product src);
}
