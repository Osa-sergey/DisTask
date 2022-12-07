package ru.serov.distask.dao.controller.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.product.ArticleListItemDTO;
import ru.serov.distask.dao.controller.model.product.ProductDTO;
import ru.serov.distask.dao.repository.enity.ArticleEntity;
import ru.serov.distask.model.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductDTOProductMapper {

    List<ArticleListItemDTO> convertArticles(List<ArticleEntity> articles);

    ArticleListItemDTO convertArticle(ArticleEntity article);

    @Mapping(target = "implement_cost", source = "implementCost")
    ProductDTO entityToDTO(Product src);
}
