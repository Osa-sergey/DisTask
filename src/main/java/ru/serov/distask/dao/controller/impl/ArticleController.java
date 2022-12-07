package ru.serov.distask.dao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.serov.distask.dao.controller.mapper.article.IArticleDTOArticleMapper;
import ru.serov.distask.dao.controller.mapper.articleentity.IArticleEntityDTOArticleEntityMapper;
import ru.serov.distask.dao.controller.mapper.articleentity.ICArticleEntityDTOArticleEntityMapper;
import ru.serov.distask.dao.controller.model.article.ArticleDTO;
import ru.serov.distask.dao.controller.model.articleentity.ArticleEntityDTO;
import ru.serov.distask.dao.controller.model.articleentity.CArticleEntityDTO;
import ru.serov.distask.dao.controller.sort.ISortComparator;
import ru.serov.distask.service.IArticleEntityService;
import ru.serov.distask.service.IArticleService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final IArticleEntityService articleEntityService;
    private final IArticleService articleService;
    private final ISortComparator<ArticleDTO> articleSortComparator;
    private final IArticleEntityDTOArticleEntityMapper articleEntityMapper;
    private final ICArticleEntityDTOArticleEntityMapper cArticleEntityMapper;
    private final IArticleDTOArticleMapper articleMapper;

    @Autowired
    public ArticleController(IArticleEntityService articleEntityService,
                             IArticleService articleService,
                             ISortComparator<ArticleDTO> restSortService,
                             IArticleEntityDTOArticleEntityMapper articleEntityMapper,
                             ICArticleEntityDTOArticleEntityMapper cArticleEntityMapper,
                             IArticleDTOArticleMapper articleMapper) {
        this.articleEntityService = articleEntityService;
        this.articleService = articleService;
        this.articleSortComparator = restSortService;
        this.articleEntityMapper = articleEntityMapper;
        this.cArticleEntityMapper = cArticleEntityMapper;
        this.articleMapper = articleMapper;
    }

    @PostMapping
    Mono<ArticleEntityDTO> createArticle(@RequestBody CArticleEntityDTO dto) {
        return articleEntityService
                .createArticle(cArticleEntityMapper.dtoToEntity(dto))
                .flatMap(product -> Mono.just(articleEntityMapper.entityToDTO(product)));
    }

    @DeleteMapping("/{id}")
    Mono<Void> deleteArticleById(@PathVariable Long id) {
        return articleEntityService
                .deleteArticleById(id);
    }

    @DeleteMapping
    Mono<Void> deleteAllArticles() {
        return articleEntityService
                .deleteAllArticles();
    }

    @DeleteMapping("/product/{id}")
    Mono<Void> deleteArticlesByProductId(@PathVariable Long id) {
        return articleEntityService
                .deleteArticlesByProductId(id);
    }

    @PatchMapping
    Mono<ArticleEntityDTO> patchArticle(@RequestBody ArticleEntityDTO dto) {
        return articleEntityService
                .patchArticle(articleEntityMapper.dtoToEntity(dto))
                .flatMap(article -> Mono.just(articleEntityMapper.entityToDTO(article)));
    }

    @PutMapping
    Mono<ArticleEntityDTO> updateArticle(@RequestBody ArticleEntityDTO dto) {
        return articleEntityService
                .updateArticle(articleEntityMapper.dtoToEntity(dto))
                .flatMap(article -> Mono.just(articleEntityMapper.entityToDTO(article)));
    }

    @GetMapping("/{id}")
    Mono<ArticleDTO> getArticleById(@PathVariable Long id) {
        return articleService
                .getArticleById(id)
                .flatMap(article -> Mono.just(articleMapper.entityToDTO(article)));
    }

    @GetMapping
    Mono<List<ArticleDTO>> getArticles(@RequestParam(value = "filter_by", required = false) String filterBy,
                                       @RequestParam(value = "sorted_by", required = false) String sortedBy) {
        Comparator<ArticleDTO> comparator = articleSortComparator.getComparator(sortedBy, allowedNamesForSort);
        return articleService
                .getAllArticles()
                .flatMap(article -> {
                    List<ArticleDTO> dtos = articleMapper.entityToDTO(article);
                    dtos = dtos.stream()
                            .filter(a -> a.getProduct_id() == 1)
                            .sorted(comparator)
                            .collect(Collectors.toList());
                    return Mono.just(dtos);
                });
    }
}