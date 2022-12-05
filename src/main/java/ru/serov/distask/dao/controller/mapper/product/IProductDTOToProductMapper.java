package ru.serov.distask.dao.controller.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.serov.distask.dao.controller.model.product.ProductDTO;
import ru.serov.distask.entity.Product;

@Mapper(componentModel = "spring")
public interface IProductDTOToProductMapper {
    @Mapping(target = "implementCost", source = "implement_cost")
    Product productDTOToProduct(ProductDTO src);

    @Mapping(target = "implement_cost", source = "implementCost")
    ProductDTO productToProductDTO(Product src);
}
