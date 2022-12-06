package ru.serov.distask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.repository.IArticleEntityRepo;
import ru.serov.distask.dao.repository.enity.ArticleEntity;
import ru.serov.distask.exception.impl.AttachedProductNotFoundException;
import ru.serov.distask.exception.impl.EntityForPatchNotFoundException;
import ru.serov.distask.exception.impl.EntityForUpdateNotFoundException;
import ru.serov.distask.exception.impl.NameNotUniqueException;
import ru.serov.distask.service.IArticleEntityService;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class ArticleEntityService implements IArticleEntityService {

    private final IArticleEntityRepo articleRepo;

    @Autowired
    public ArticleEntityService(IArticleEntityRepo articleRepo) {
        this.articleRepo = articleRepo;
    }


    @Override
    public Mono<ArticleEntity> createArticle(ArticleEntity articleEntity) {
        return Mono.just(articleEntity).flatMap(p -> {
            p.setCreateDate(LocalDate.now());
            return articleRepo.save(articleEntity);
        }).onErrorMap(e -> {
            if (e instanceof DataIntegrityViolationException) {
                if (Objects.requireNonNull(e.getMessage()).contains("[23505]")) {
                    throw new NameNotUniqueException(articleEntity.getName());
                } else if (Objects.requireNonNull(e.getMessage()).contains("[23503]")) {
                    throw new AttachedProductNotFoundException(articleEntity.getProductId().toString());
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
    public Mono<ArticleEntity> patchArticle(ArticleEntity articleEntity) {
        return Mono.just(articleEntity)
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
                                        if (Objects.requireNonNull(e.getMessage()).contains("[23503]")) {
                                            throw new AttachedProductNotFoundException(articleEntity.getProductId().toString());
                                        } else {
                                            throw new NameNotUniqueException(articleEntity.getName());
                                        }
                                    } else return e;
                                })
                );
    }

    @Override
    public Mono<ArticleEntity> updateArticle(ArticleEntity articleEntity) {
        return Mono.just(articleEntity)
                .flatMap(a -> {
                    if (a.getId() == null ||
                            a.getProductId() == null ||
                            a.getName() == null ||
                            a.getContent() == null ||
                            a.getCreateDate() == null)
                        return Mono.error(new IllegalArgumentException());
                    return Mono.just(a);
                }).flatMap(articleRepo::save)
                .onErrorMap(e -> {
                    if (e instanceof DataIntegrityViolationException) {
                        if (Objects.requireNonNull(e.getMessage()).contains("[23503]")) {
                            throw new AttachedProductNotFoundException(articleEntity.getProductId().toString());
                        } else {
                            throw new NameNotUniqueException(articleEntity.getName());
                        }
                    } else if (e instanceof TransientDataAccessResourceException) {
                        throw new EntityForUpdateNotFoundException();
                    } else {
                        return e;
                    }
                });
    }

    @Override
    public Mono<ArticleEntity> getArticleById(Long id) {
        return articleRepo.findById(id);
    }

    @Override
    public Flux<ArticleEntity> getArticlesByProductId(Long productId) {
        return articleRepo.findAllByProductId(productId);
    }
}
