package ru.serov.distask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.repository.IProductRepo;
import ru.serov.distask.entity.Product;
import ru.serov.distask.exception.impl.*;
import ru.serov.distask.service.IProductService;

import java.util.Objects;

@Service
public class ProductService implements IProductService {

    private final IProductRepo productRepo;

    @Autowired
    public ProductService(IProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Mono<Product> createProduct(Product product) throws NameNotUniqueException {
        return productRepo.save(product).onErrorMap(e -> {
            if (e instanceof DataIntegrityViolationException) {
                if (Objects.requireNonNull(e.getMessage()).contains("[23505]")) {
                    throw new NameNotUniqueException(product.getName());
                } else throw new IllegalArgumentException();
            } else return e;
        });
    }

    @Override
    public Mono<Void> deleteProductById(Long id) throws HasAttachedArticlesException {
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
                                .switchIfEmpty(Mono.error(new EntityForPatchNotFoundException()))
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

    @Override
    public Mono<Product> updateProduct(Product product) {
        return Mono.just(product)
                .flatMap(p -> {
                    if (p.getId() == null ||
                            p.getName() == null ||
                            p.getDescription() == null ||
                            p.getImplementCost() == null)
                        return Mono.error(new IllegalArgumentException());
                    return Mono.just(p);
                }).flatMap(productRepo::save)
                .onErrorMap(e -> {
                    if (e instanceof DataIntegrityViolationException) {
                        throw new NameNotUniqueException(product.getName());
                    } else if (e instanceof TransientDataAccessResourceException) {
                        throw new EntityForUpdateNotFoundException();
                    } else {
                        return e;
                    }
                });
    }
}
