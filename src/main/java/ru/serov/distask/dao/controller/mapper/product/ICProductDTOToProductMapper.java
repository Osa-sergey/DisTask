package ru.serov.distask.dao.controller.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.product.CProductDTO;
import ru.serov.distask.entity.Product;

@Mapper(componentModel = "spring")
public interface ICProductDTOToProductMapper {
    @Mapping(target = "implementCost", source = "implement_cost")
    Product cProductDTOToProduct(CProductDTO src);

}