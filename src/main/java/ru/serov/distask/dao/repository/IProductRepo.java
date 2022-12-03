package ru.serov.distask.dao.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.serov.distask.entity.Product;

public interface IProductRepo extends ReactiveCrudRepository<Product, Long> {
}
