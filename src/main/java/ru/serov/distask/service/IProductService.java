package ru.serov.distask.service;

import reactor.core.publisher.Mono;
import ru.serov.distask.entity.Product;
import ru.serov.distask.exception.impl.EntityForPatchNotFoundException;
import ru.serov.distask.exception.impl.EntityForUpdateNotFoundException;
import ru.serov.distask.exception.impl.NameNotUniqueException;
import ru.serov.distask.exception.impl.ProductsHaveAttachedArticlesException;

public interface IProductService {
    Mono<Product> createProduct(Product product) throws NameNotUniqueException;

    Mono<Void> deleteProductById(Long id) throws ProductsHaveAttachedArticlesException;

    Mono<Void> deleteAllProducts() throws ProductsHaveAttachedArticlesException;

    Mono<Product> patchProduct(Product product) throws NameNotUniqueException, EntityForPatchNotFoundException;

    Mono<Product> updateProduct(Product product) throws NameNotUniqueException, EntityForUpdateNotFoundException;
}
