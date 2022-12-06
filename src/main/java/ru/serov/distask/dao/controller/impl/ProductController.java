package ru.serov.distask.dao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.controller.mapper.productentity.ICProductEntityDTOProductEntityMapper;
import ru.serov.distask.dao.controller.mapper.productentity.IProductEntityDTOProductEntityMapper;
import ru.serov.distask.dao.controller.model.productentity.CProductEntityDTO;
import ru.serov.distask.dao.controller.model.productentity.ProductEntityDTO;
import ru.serov.distask.service.IProductEntityService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final IProductEntityService productEntityService;
    private final ICProductEntityDTOProductEntityMapper cProductEntityMapper;
    private final IProductEntityDTOProductEntityMapper productEntityMapper;

    @Autowired
    public ProductController(IProductEntityService productEntityService,
                             ICProductEntityDTOProductEntityMapper cProductEntityMapper,
                             IProductEntityDTOProductEntityMapper productEntityMapper) {
        this.productEntityService = productEntityService;
        this.cProductEntityMapper = cProductEntityMapper;
        this.productEntityMapper = productEntityMapper;
    }

    @PostMapping
    Mono<ProductEntityDTO> createProduct(@RequestBody CProductEntityDTO dto) {
        return productEntityService
                .createProduct(cProductEntityMapper.dtoToEntity(dto))
                .flatMap(product -> Mono.just(productEntityMapper.entityToDTO(product)));
    }

    @DeleteMapping("/{id}")
    Mono<Void> deleteProductById(@PathVariable Long id) {
        return productEntityService
                .deleteProductById(id);
    }

    @DeleteMapping
    Mono<Void> deleteAllProducts() {
        return productEntityService
                .deleteAllProducts();
    }

    @PatchMapping
    Mono<ProductEntityDTO> patchProduct(@RequestBody ProductEntityDTO dto) {
        return productEntityService
                .patchProduct(productEntityMapper.dotToEntity(dto))
                .flatMap(product -> Mono.just(productEntityMapper.entityToDTO(product)));
    }

    @PutMapping
    Mono<ProductEntityDTO> updateProduct(@RequestBody ProductEntityDTO dto) {
        return productEntityService
                .updateProduct(productEntityMapper.dotToEntity(dto))
                .flatMap(product -> Mono.just(productEntityMapper.entityToDTO(product)));
    }
}
