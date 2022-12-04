package ru.serov.distask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.repository.IProductRepo;
import ru.serov.distask.entity.Product;
import ru.serov.distask.exception.impl.HasAttachedArticlesException;
import ru.serov.distask.exception.impl.NameNotUniqueException;
import ru.serov.distask.exception.impl.ProductsHaveAttachedArticlesException;
import ru.serov.distask.service.IProductService;

@Service
public class ProductService implements IProductService {

    private final IProductRepo productRepo;

    @Autowired
    public ProductService(IProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Mono<Product> createProduct(Product Product) throws NameNotUniqueException {
        return productRepo.save(Product).onErrorMap(e -> new NameNotUniqueException(Product.getName()));
    }

    @Override
    public Mono<Void> deleteProductById(Long id) throws ProductsHaveAttachedArticlesException {
        return productRepo.deleteById(id).onErrorMap(e -> new HasAttachedArticlesException(id.toString()));
    }

    @Override
    public Mono<Void> deleteAllProducts() throws ProductsHaveAttachedArticlesException {
        return productRepo.deleteAll().onErrorMap(e -> new ProductsHaveAttachedArticlesException());
    }


}
