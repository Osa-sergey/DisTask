package ru.serov.distask.dao.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.ProductDTO;
import ru.serov.distask.entity.Product;

@Mapper(componentModel = "spring")
public interface IProductDTOToProductMapper {
    @Mapping(target = "implementCost", source = "src.implement_cost")
    Product productDTOToProduct(ProductDTO src);

    @Mapping(target = "implement_cost", source = "src.implementCost")
    ProductDTO productToProductDTO(Product src);
}
