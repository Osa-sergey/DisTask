package ru.serov.distask.dao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.controller.mapper.article.IArticleDTOToProductMapper;
import ru.serov.distask.dao.controller.mapper.article.ICArticleDTOToArticleMapper;
import ru.serov.distask.dao.controller.model.article.ArticleDTO;
import ru.serov.distask.dao.controller.model.article.CArticleDTO;
import ru.serov.distask.service.IArticleService;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final IArticleService articleService;
    private final IArticleDTOToProductMapper articleMapper;
    private final ICArticleDTOToArticleMapper cArticleMapper;

    @Autowired
    public ArticleController(IArticleService articleService, IArticleDTOToProductMapper articleMapper, ICArticleDTOToArticleMapper cArticleMapper) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
        this.cArticleMapper = cArticleMapper;
    }

    @PostMapping
    Mono<ArticleDTO> createArticle(@RequestBody CArticleDTO dto) {
        return articleService
                .createArticle(cArticleMapper.cArticleDTOToArticle(dto))
                .flatMap(product -> Mono.just(articleMapper.articleToArticleDTO(product)));
    }
}
