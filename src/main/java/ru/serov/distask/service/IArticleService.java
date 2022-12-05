package ru.serov.distask.service;

import reactor.core.publisher.Mono;
import ru.serov.distask.entity.Article;
import ru.serov.distask.exception.impl.AttachedProductNotFoundException;
import ru.serov.distask.exception.impl.NameNotUniqueException;

public interface IArticleService {
    Mono<Article> createArticle(Article article) throws NameNotUniqueException, AttachedProductNotFoundException;

    Mono<Void> deleteArticleById(Long id);

    Mono<Void> deleteAllArticles();

    Mono<Void> deleteArticlesByProductId(Long productId);

    Mono<Article> patchArticle(Article article) throws AttachedProductNotFoundException;
}
