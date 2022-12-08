package ru.serov.distask.dao.controller.mapper.productentity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.productentity.CProductEntityDTO;
import ru.serov.distask.dao.repository.enity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ICProductEntityDTOProductEntityMapper {
    @Mapping(target = "implementCost", source = "implement_cost")
    ProductEntity dtoToEntity(CProductEntityDTO src);

}
