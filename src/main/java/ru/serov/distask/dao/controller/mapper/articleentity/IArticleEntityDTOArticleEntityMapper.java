package ru.serov.distask.dao.controller.mapper.articleentity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.articleentity.ArticleEntityDTO;
import ru.serov.distask.dao.repository.enity.ArticleEntity;

@Mapper(componentModel = "spring")
public interface IArticleEntityDTOArticleEntityMapper {
    @Mapping(target = "productId", source = "product_id")
    @Mapping(target = "createDate", source = "create_date")
    ArticleEntity dtoToEntity(ArticleEntityDTO src);

    @Mapping(target = "product_id", source = "productId")
    @Mapping(target = "create_date", source = "createDate")
    ArticleEntityDTO entityToDTO(ArticleEntity src);
}
