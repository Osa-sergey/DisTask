package ru.serov.distask.dao.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.serov.distask.entity.Article;

public interface IArticleRepo extends ReactiveCrudRepository<Article, Long> {
}
