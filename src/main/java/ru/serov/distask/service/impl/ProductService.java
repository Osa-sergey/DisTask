package ru.serov.distask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.controller.mapper.productentity.IProductAndArticleEntityToProductMapper;
import ru.serov.distask.model.Product;
import ru.serov.distask.service.IArticleEntityService;
import ru.serov.distask.service.IProductEntityService;
import ru.serov.distask.service.IProductService;

@Service
public class ProductService implements IProductService {

    private final IArticleEntityService articleEntityService;
    private final IProductEntityService productEntityService;
    private final IProductAndArticleEntityToProductMapper mapper;

    @Autowired
    public ProductService(IArticleEntityService articleEntityService,
                          IProductEntityService productEntityService,
                          IProductAndArticleEntityToProductMapper mapper) {
        this.articleEntityService = articleEntityService;
        this.productEntityService = productEntityService;
        this.mapper = mapper;
    }

    @Override
    public Mono<Product> getProductById(Long id) {
        return productEntityService
                .getProductById(id)
                .flatMap(product -> articleEntityService
                        .getArticlesByProductId(id)
                        .collectList()
                        .map(articles -> mapper.entityToProduct(product, articles)));

    }

    @Override
    public Flux<Product> getAllProducts() {
        return productEntityService
                .getAllProducts()
                .flatMap(product -> articleEntityService
                        .getArticlesByProductId(product.getId())
                        .collectList()
                        .map(articles -> mapper.entityToProduct(product, articles)));
    }
}
