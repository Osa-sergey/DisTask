package ru.serov.distask.dao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("/{id}")
    Mono<Void> deleteProductById(@PathVariable Long id) {
        return productService
                .deleteProductById(id);
    }

    @DeleteMapping
    Mono<Void> deleteAllProducts() {
        return productService
                .deleteAllProducts();
    }

    @PatchMapping
    Mono<ProductDTO> patchProduct(@RequestBody ProductDTO dto) {
        return productService
                .patchProduct(productMapper.productDTOToProduct(dto))
                .flatMap(product -> Mono.just(productMapper.productToProductDTO(product)));
    }

    @PutMapping
    Mono<ProductDTO> updateProduct(@RequestBody ProductDTO dto) {
        return productService
                .updateProduct(productMapper.productDTOToProduct(dto))
                .flatMap(product -> Mono.just(productMapper.productToProductDTO(product)));
    }
}
