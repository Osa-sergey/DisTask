package ru.serov.distask.dao.controller.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reactor.core.publisher.Flux;
import ru.serov.distask.dao.controller.model.product.ProductDTO;
import ru.serov.distask.dao.repository.enity.ArticleEntity;
import ru.serov.distask.model.Product;

@Mapper(componentModel = "spring")
public interface IProductDTOProductMapper {
    @Named("convertArticles")
    static Flux<Long> convertArticles(Flux<ArticleEntity> articles) {
        return articles.map(ArticleEntity::getId);
    }

    @Mapping(target = "implement_cost", source = "implementCost")
    @Mapping(target = "article_ids", source = "articles", qualifiedByName = "convertArticles")
    ProductDTO entityToDTO(Product src);
}
