package ru.serov.distask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        return productRepo.save(Product).onErrorMap(e -> {
            if (e instanceof DataIntegrityViolationException) {
                throw new NameNotUniqueException(Product.getName());
            } else return e;
        });
    }

    @Override
    public Mono<Void> deleteProductById(Long id) throws ProductsHaveAttachedArticlesException {
        return productRepo.deleteById(id).onErrorMap(e -> {
            if (e instanceof DataIntegrityViolationException) {
                throw new HasAttachedArticlesException(id.toString());
            } else return e;
        });
    }

    @Override
    public Mono<Void> deleteAllProducts() throws ProductsHaveAttachedArticlesException {
        return productRepo.deleteAll().onErrorMap(e -> {
            if (e instanceof DataIntegrityViolationException) {
                throw new ProductsHaveAttachedArticlesException();
            } else return e;
        });
    }

    @Override
    public Mono<Product> patchProduct(Product product) throws NameNotUniqueException {
        return Mono.just(product)
                .flatMap(n ->
                        productRepo.findById(n.getId())
                                .flatMap(o -> {
                                    o.setDescription(n.getDescription() == null ? o.getDescription() : n.getDescription());
                                    o.setImplementCost(n.getImplementCost() == null ? o.getImplementCost() : n.getImplementCost());
                                    o.setName(n.getName() == null ? o.getName() : n.getName());
                                    return productRepo.save(o);
                                }).onErrorMap(e -> {
                                    if (e instanceof DataIntegrityViolationException) {
                                        throw new NameNotUniqueException(product.getName());
                                    } else return e;
                                })
                );
    }
}
