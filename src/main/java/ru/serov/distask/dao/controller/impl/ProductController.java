package ru.serov.distask.dao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.controller.mapper.product.IProductDTOProductMapper;
import ru.serov.distask.dao.controller.mapper.productentity.ICProductEntityDTOProductEntityMapper;
import ru.serov.distask.dao.controller.mapper.productentity.IProductEntityDTOProductEntityMapper;
import ru.serov.distask.dao.controller.model.product.ProductDTO;
import ru.serov.distask.dao.controller.model.productentity.CProductEntityDTO;
import ru.serov.distask.dao.controller.model.productentity.ProductEntityDTO;
import ru.serov.distask.dao.controller.sort.ISortComparator;
import ru.serov.distask.service.IProductEntityService;
import ru.serov.distask.service.IProductService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final IProductEntityService productEntityService;
    private final IProductService productService;
    private final ISortComparator<ProductDTO> productSortComparator;
    private final ICProductEntityDTOProductEntityMapper cProductEntityMapper;
    private final IProductEntityDTOProductEntityMapper productEntityMapper;
    private final IProductDTOProductMapper productMapper;
    private final List<String> allowedNamesForSort = Arrays.asList("id", "name", "description", "implement_cost");

    @Autowired
    public ProductController(IProductEntityService productEntityService,
                             IProductService productService,
                             ISortComparator<ProductDTO> productSortComparator,
                             ICProductEntityDTOProductEntityMapper cProductEntityMapper,
                             IProductEntityDTOProductEntityMapper productEntityMapper,
                             IProductDTOProductMapper mapper) {
        this.productEntityService = productEntityService;
        this.productService = productService;
        this.productSortComparator = productSortComparator;
        this.cProductEntityMapper = cProductEntityMapper;
        this.productEntityMapper = productEntityMapper;
        this.productMapper = mapper;
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

    @GetMapping("/{id}")
    Mono<ProductDTO> getProductById(@PathVariable Long id) {
        return productService
                .getProductById(id)
                .flatMap(product -> Mono.just(productMapper.entityToDTO(product)));
    }

    @GetMapping
    Mono<List<ProductDTO>> getProducts(@RequestParam(value = "filter_by", required = false) String filterBy,
                                       @RequestParam(value = "sorted_by", required = false) String sortedBy) {
        Comparator<ProductDTO> comparator = productSortComparator.getComparator(sortedBy, allowedNamesForSort);
        return productService
                .getAllProducts()
                .flatMap(product -> {
                    List<ProductDTO> dtos = productMapper.entityToDTO(product);
                    dtos = dtos.stream()
                            .sorted(comparator)
                            .collect(Collectors.toList());
                    return Mono.just(dtos);
                });
    }
}
