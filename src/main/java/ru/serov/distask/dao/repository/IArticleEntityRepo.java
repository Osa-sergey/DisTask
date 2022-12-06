package ru.serov.distask.dao.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.repository.enity.ArticleEntity;

public interface IArticleEntityRepo extends ReactiveCrudRepository<ArticleEntity, Long> {

    @Query(value = "DELETE FROM company.articles WHERE product_id = $1")
    Mono<Void> deleteArticlesByProductId(Long productId);

    @Query(value = "SELECT * FROM company.articles WHERE  product_id = $1")
    Flux<ArticleEntity> findAllByProductId(Long productId);
}
