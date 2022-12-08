package ru.serov.distask.dao.controller.mapper.productentity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.productentity.ProductEntityDTO;
import ru.serov.distask.dao.repository.enity.ProductEntity;

@Mapper(componentModel = "spring")
public interface IProductEntityDTOProductEntityMapper {
    @Mapping(target = "implementCost", source = "implement_cost")
    ProductEntity dotToEntity(ProductEntityDTO src);

    @Mapping(target = "implement_cost", source = "implementCost")
    ProductEntityDTO entityToDTO(ProductEntity src);
}
