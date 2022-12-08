package ru.serov.distask.service;

import reactor.core.publisher.Mono;
import ru.serov.distask.model.Article;

import java.util.List;

public interface IArticleService {
    Mono<Article> getArticleById(Long id);

    Mono<List<Article>> getAllArticles();
}
