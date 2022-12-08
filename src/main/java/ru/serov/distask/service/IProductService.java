package ru.serov.distask.service;

import reactor.core.publisher.Mono;
import ru.serov.distask.model.Product;

import java.util.List;

public interface IProductService {
    Mono<Product> getProductById(Long id);

    Mono<List<Product>> getAllProducts();
}
