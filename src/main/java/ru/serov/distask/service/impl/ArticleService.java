package ru.serov.distask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.repository.IArticleRepo;
import ru.serov.distask.entity.Article;
import ru.serov.distask.exception.impl.AttachedProductNotFoundException;
import ru.serov.distask.exception.impl.NameNotUniqueException;
import ru.serov.distask.service.IArticleService;

import java.util.Date;
import java.util.Objects;

@Service
public class ArticleService implements IArticleService {

    private final IArticleRepo articleRepo;

    @Autowired
    public ArticleService(IArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }


    @Override
    public Mono<Article> createArticle(Article article) {
        return Mono.just(article).flatMap(p -> {
            p.setCreateDate(new Date());
            return articleRepo.save(article);
        }).onErrorMap(e -> {
            if (e instanceof DataIntegrityViolationException) {
                if (Objects.requireNonNull(e.getMessage()).contains("[23505]")) {
                    throw new NameNotUniqueException(article.getName());
                } else if (Objects.requireNonNull(e.getMessage()).contains("[23503]")) {
                    throw new AttachedProductNotFoundException(article.getProductId().toString());
                } else throw new IllegalArgumentException();
            } else return e;
        });
    }

    @Override
    public Mono<Void> deleteArticleById(Long id) {
        return articleRepo.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAllArticles() {
        return articleRepo.deleteAll();
    }
}
