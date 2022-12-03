package ru.serov.distask.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.serov.distask.entity.Product;
import ru.serov.distask.exception.impl.NameNotUniqueException;
import ru.serov.distask.service.IProductService;

@Service
public class ProductService implements IProductService {

    @Override
    public Mono<Product> createProduct(Product product) throws NameNotUniqueException {
        return null;
    }
}
