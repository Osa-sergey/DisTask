package ru.serov.distask.dao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.controller.mapper.ICProductDTOToProductMapper;
import ru.serov.distask.dao.controller.mapper.IProductDTOToProductMapper;
import ru.serov.distask.dao.controller.model.CProductDTO;
import ru.serov.distask.dao.controller.model.ProductDTO;
import ru.serov.distask.service.IProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final IProductService productService;
    private final ICProductDTOToProductMapper cProductMapper;
    private final IProductDTOToProductMapper productMapper;

    @Autowired
    public ProductController(IProductService productService, ICProductDTOToProductMapper cProductMapper, IProductDTOToProductMapper productMapper) {
        this.productService = productService;
        this.cProductMapper = cProductMapper;
        this.productMapper = productMapper;
    }

    @PostMapping
    Mono<ProductDTO> createProduct(@RequestBody CProductDTO dto) {
        return productService
                .createProduct(cProductMapper.cProductDTOToProduct(dto))
                .flatMap(product -> Mono.just(productMapper.productToProductDTO(product)));
    }
}
