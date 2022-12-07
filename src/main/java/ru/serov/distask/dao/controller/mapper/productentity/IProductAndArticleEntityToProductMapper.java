package ru.serov.distask.dao.controller.mapper.productentity;

import org.mapstruct.Mapper;
import ru.serov.distask.dao.repository.enity.ArticleEntity;
import ru.serov.distask.dao.repository.enity.ProductEntity;
import ru.serov.distask.model.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductAndArticleEntityToProductMapper {


    Product entityToProduct(ProductEntity product, List<ArticleEntity> articles);
}
