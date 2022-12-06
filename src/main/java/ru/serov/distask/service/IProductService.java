package ru.serov.distask.service;

import reactor.core.publisher.Mono;
import ru.serov.distask.model.Product;

public interface IProductService {
    Mono<Product> getProductById(Long id);
}
