package ru.serov.distask.service;

import reactor.core.publisher.Mono;
import ru.serov.distask.entity.Product;
import ru.serov.distask.exception.impl.NameNotUniqueException;

public interface IProductService {
    Mono<Product> createProduct(Product product) throws NameNotUniqueException;
}
