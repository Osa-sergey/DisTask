package ru.serov.distask.dao.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.serov.distask.dao.repository.enity.ProductEntity;

public interface IProductEntityRepo extends ReactiveCrudRepository<ProductEntity, Long> {
}
