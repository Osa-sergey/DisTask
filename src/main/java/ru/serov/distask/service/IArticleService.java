package ru.serov.distask.service;

import reactor.core.publisher.Mono;
import ru.serov.distask.model.Article;

public interface IArticleService {
    Mono<Article> getArticleById(Long id);
}
