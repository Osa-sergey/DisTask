package ru.serov.distask.dao.controller.mapper.productentity;

import org.mapstruct.Mapper;
import reactor.core.publisher.Flux;
import ru.serov.distask.dao.repository.enity.ArticleEntity;
import ru.serov.distask.dao.repository.enity.ProductEntity;
import ru.serov.distask.model.Product;

@Mapper(componentModel = "spring")
public interface IProductAndArticleEntityToProductMapper {


    Product entityToProduct(ProductEntity product, Flux<ArticleEntity> articles);
}
