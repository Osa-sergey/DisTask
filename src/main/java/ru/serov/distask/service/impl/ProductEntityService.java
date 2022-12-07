package ru.serov.distask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.repository.IProductEntityRepo;
import ru.serov.distask.dao.repository.enity.ProductEntity;
import ru.serov.distask.exception.impl.*;
import ru.serov.distask.service.IProductEntityService;

import java.util.Objects;

@Service
public class ProductEntityService implements IProductEntityService {

    private final IProductEntityRepo productRepo;

    @Autowired
    public ProductEntityService(IProductEntityRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Mono<ProductEntity> createProduct(ProductEntity productEntity) throws NameNotUniqueException {
        return productRepo.save(productEntity).onErrorMap(e -> {
            if (e instanceof DataIntegrityViolationException) {
                if (Objects.requireNonNull(e.getMessage()).contains("[23505]")) {
                    throw new NameNotUniqueException(productEntity.getName());
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
    public Mono<ProductEntity> patchProduct(ProductEntity productEntity) throws NameNotUniqueException {
        return Mono.just(productEntity)
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
                                        throw new NameNotUniqueException(productEntity.getName());
                                    } else return e;
                                })
                );
    }

    @Override
    public Mono<ProductEntity> updateProduct(ProductEntity productEntity) {
        return Mono.just(productEntity)
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
                        throw new NameNotUniqueException(productEntity.getName());
                    } else if (e instanceof TransientDataAccessResourceException) {
                        throw new EntityForUpdateNotFoundException();
                    } else {
                        return e;
                    }
                });
    }

    @Override
    public Mono<ProductEntity> getProductById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public Flux<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }
}
