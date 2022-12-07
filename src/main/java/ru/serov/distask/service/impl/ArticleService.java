package ru.serov.distask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.controller.mapper.articleentity.IArticleAndProductEntityToArticleMapper;
import ru.serov.distask.model.Article;
import ru.serov.distask.service.IArticleEntityService;
import ru.serov.distask.service.IArticleService;
import ru.serov.distask.service.IProductEntityService;

import java.util.List;

@Service
public class ArticleService implements IArticleService {

    private final IArticleEntityService articleEntityService;
    private final IProductEntityService productEntityService;
    private final IArticleAndProductEntityToArticleMapper mapper;

    @Autowired
    public ArticleService(IArticleEntityService articleEntityService,
                          IProductEntityService productEntityService,
                          IArticleAndProductEntityToArticleMapper mapper) {
        this.articleEntityService = articleEntityService;
        this.productEntityService = productEntityService;
        this.mapper = mapper;
    }

    @Override
    public Mono<Article> getArticleById(Long id) {
        return articleEntityService
                .getArticleById(id)
                .flatMap(article -> productEntityService.getProductById(article.getProductId())
                        .map(product -> mapper.entityToArticle(article, product)));
    }

    @Override
    public Mono<List<Article>> getAllArticles() {
        return articleEntityService
                .getAllArticles()
                .flatMap(article -> productEntityService.getProductById(article.getProductId())
                        .map(product -> mapper.entityToArticle(article, product))).collectList();
    }
}
