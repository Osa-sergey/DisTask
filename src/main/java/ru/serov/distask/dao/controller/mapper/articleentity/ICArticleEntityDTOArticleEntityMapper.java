package ru.serov.distask.dao.controller.mapper.articleentity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.articleentity.CArticleEntityDTO;
import ru.serov.distask.dao.repository.enity.ArticleEntity;

@Mapper(componentModel = "spring")
public interface ICArticleEntityDTOArticleEntityMapper {
    @Mapping(target = "productId", source = "product_id")
    ArticleEntity dtoToEntity(CArticleEntityDTO src);
}
