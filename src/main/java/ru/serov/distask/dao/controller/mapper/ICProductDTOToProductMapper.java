package ru.serov.distask.dao.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.CProductDTO;
import ru.serov.distask.entity.Product;

@Mapper(componentModel = "spring")
public interface ICProductDTOToProductMapper {
    @Mapping(target = "implementCost", source = "src.implement_cost")
    Product cProductDTOToProduct(CProductDTO src);

}
