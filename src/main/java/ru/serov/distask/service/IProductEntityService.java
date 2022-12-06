package ru.serov.distask.service;

import reactor.core.publisher.Mono;
import ru.serov.distask.dao.repository.enity.ProductEntity;
import ru.serov.distask.exception.impl.*;

public interface IProductEntityService {
    Mono<ProductEntity> createProduct(ProductEntity productEntity) throws NameNotUniqueException;

    Mono<Void> deleteProductById(Long id) throws HasAttachedArticlesException;

    Mono<Void> deleteAllProducts() throws ProductsHaveAttachedArticlesException;

    Mono<ProductEntity> patchProduct(ProductEntity productEntity) throws NameNotUniqueException, EntityForPatchNotFoundException;

    Mono<ProductEntity> updateProduct(ProductEntity productEntity) throws NameNotUniqueException, EntityForUpdateNotFoundException;

    Mono<ProductEntity> getProductById(Long id);
}
