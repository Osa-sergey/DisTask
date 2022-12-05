package ru.serov.distask.service;

import reactor.core.publisher.Mono;
import ru.serov.distask.entity.Product;
import ru.serov.distask.exception.impl.*;

public interface IProductService {
    Mono<Product> createProduct(Product product) throws NameNotUniqueException;

    Mono<Void> deleteProductById(Long id) throws HasAttachedArticlesException;

    Mono<Void> deleteAllProducts() throws ProductsHaveAttachedArticlesException;

    Mono<Product> patchProduct(Product product) throws NameNotUniqueException, EntityForPatchNotFoundException;

    Mono<Product> updateProduct(Product product) throws NameNotUniqueException, EntityForUpdateNotFoundException;
}
