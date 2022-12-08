package ru.serov.distask.dao.controller.filter;

import org.springframework.stereotype.Component;
import ru.serov.distask.dao.controller.model.article.ArticleDTO;
import ru.serov.distask.service.model.FilterParam;

import java.time.LocalDate;
import java.util.List;

@Component
public class ArticleDTOFilter implements IDTOFilter<ArticleDTO> {

    @Override
    public boolean filterDTO(List<FilterParam> conds, ArticleDTO item) {
        int failsCount = 0;
        for (FilterParam cond : conds) {
            switch (cond.getFieldName()) {
                case "id":
                    if (!checkLongConds(cond, item.getId())) failsCount++;
                    break;
                case "product_id":
                    if (!checkLongConds(cond, item.getProduct_id())) failsCount++;
                    break;
                case "article_name":
                    if (!checkStringConds(cond, item.getArticle_name())) failsCount++;
                    break;
                case "article_content":
                    if (!checkStringConds(cond, item.getArticle_content())) failsCount++;
                    break;
                case "create_date":
                    if (!checkLocalDateConds(cond, LocalDate.parse(item.getCreate_date()))) failsCount++;
                    break;
                case "product_name":
                    if (!checkStringConds(cond, item.getProduct_name())) failsCount++;
                    break;
                case "product_description":
                    if (!checkStringConds(cond, item.getProduct_description())) failsCount++;
                    break;
                case "product_implement_cost":
                    if (!checkFloatConds(cond, item.getProduct_implement_cost())) failsCount++;
                    break;
            }
            if (failsCount > 0) return false;
        }
        return true;
    }
}


