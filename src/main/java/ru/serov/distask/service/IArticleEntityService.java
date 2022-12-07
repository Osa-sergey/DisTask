package ru.serov.distask.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.repository.enity.ArticleEntity;
import ru.serov.distask.exception.impl.AttachedProductNotFoundException;
import ru.serov.distask.exception.impl.EntityForPatchNotFoundException;
import ru.serov.distask.exception.impl.EntityForUpdateNotFoundException;
import ru.serov.distask.exception.impl.NameNotUniqueException;

public interface IArticleEntityService {
    Mono<ArticleEntity> createArticle(ArticleEntity articleEntity) throws NameNotUniqueException, AttachedProductNotFoundException;

    Mono<Void> deleteArticleById(Long id);

    Mono<Void> deleteAllArticles();

    Mono<Void> deleteArticlesByProductId(Long productId);

    Mono<ArticleEntity> patchArticle(ArticleEntity articleEntity) throws AttachedProductNotFoundException,
            EntityForPatchNotFoundException, NameNotUniqueException;

    Mono<ArticleEntity> updateArticle(ArticleEntity articleEntity) throws AttachedProductNotFoundException,
            EntityForUpdateNotFoundException, NameNotUniqueException;

    Mono<ArticleEntity> getArticleById(Long id);

    Flux<ArticleEntity> getArticlesByProductId(Long productId);

    Flux<ArticleEntity> getAllArticles();
}
