package ru.serov.distask.dao.controller.sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.serov.distask.dao.controller.model.article.ArticleDTO;
import ru.serov.distask.service.IRESTSortService;
import ru.serov.distask.service.model.Ordering;
import ru.serov.distask.service.model.SortParam;

import java.util.Comparator;
import java.util.List;

@Component
public class ArticleDTOSortComparator implements ISortComparator<ArticleDTO> {
    private final IRESTSortService sortService;

    @Autowired
    public ArticleDTOSortComparator(IRESTSortService sortService) {
        this.sortService = sortService;
    }


    @Override
    public Comparator<ArticleDTO> getComparator(String sortedBy, List<String> allowedNames) {
        Comparator<ArticleDTO> comparator = (a1, a2) -> 0;
        if (sortedBy == null || sortedBy.isEmpty()) return comparator;
        List<SortParam> params = sortService.getSortParams(sortedBy, allowedNames);
        for (SortParam param : params) {
            switch (param.getName()) {
                case "id":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ArticleDTO::getId, Comparator.reverseOrder())
                            : comparator.thenComparing(ArticleDTO::getId);
                    break;
                case "product_id":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ArticleDTO::getProduct_id, Comparator.reverseOrder())
                            : comparator.thenComparing(ArticleDTO::getProduct_id);
                    break;
                case "article_name":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ArticleDTO::getArticle_name, Comparator.reverseOrder())
                            : comparator.thenComparing(ArticleDTO::getArticle_name);
                    break;
                case "article_content":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ArticleDTO::getArticle_content, Comparator.reverseOrder())
                            : comparator.thenComparing(ArticleDTO::getArticle_content);
                    break;
                case "create_date":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ArticleDTO::getCreate_date, Comparator.reverseOrder())
                            : comparator.thenComparing(ArticleDTO::getCreate_date);
                    break;
                case "product_name":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ArticleDTO::getProduct_name, Comparator.reverseOrder())
                            : comparator.thenComparing(ArticleDTO::getProduct_name);
                    break;
                case "product_description":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ArticleDTO::getProduct_description, Comparator.reverseOrder())
                            : comparator.thenComparing(ArticleDTO::getProduct_description);
                    break;
                case "product_implement_cost":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ArticleDTO::getProduct_implement_cost, Comparator.reverseOrder())
                            : comparator.thenComparing(ArticleDTO::getProduct_implement_cost);
                    break;
            }
        }
        return comparator;
    }
}

