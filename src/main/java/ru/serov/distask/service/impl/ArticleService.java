package ru.serov.distask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.repository.IArticleRepo;
import ru.serov.distask.entity.Article;
import ru.serov.distask.exception.impl.AttachedProductNotFoundException;
import ru.serov.distask.exception.impl.EntityForPatchNotFoundException;
import ru.serov.distask.exception.impl.NameNotUniqueException;
import ru.serov.distask.service.IArticleService;

import java.time.LocalDate;
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
            p.setCreateDate(LocalDate.now());
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

    @Override
    public Mono<Void> deleteArticlesByProductId(Long productId) {
        return articleRepo.deleteArticlesByProductId(productId);
    }

    @Override
    public Mono<Article> patchArticle(Article article) {
        return Mono.just(article)
                .flatMap(n ->
                        articleRepo.findById(n.getId())
                                .switchIfEmpty(Mono.error(new EntityForPatchNotFoundException()))
                                .flatMap(o -> {
                                    o.setProductId(n.getProductId() == null ? o.getProductId() : n.getProductId());
                                    o.setName(n.getName() == null ? o.getName() : n.getName());
                                    o.setContent(n.getContent() == null ? o.getContent() : n.getContent());
                                    o.setCreateDate(n.getCreateDate() == null ? o.getCreateDate() : n.getCreateDate());
                                    return articleRepo.save(o);
                                }).onErrorMap(e -> {
                                    if (e instanceof DataIntegrityViolationException) {
                                        throw new AttachedProductNotFoundException(article.getProductId().toString());
                                    } else return e;
                                })
                );
    }


}
