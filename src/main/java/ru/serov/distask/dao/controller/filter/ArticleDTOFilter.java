package ru.serov.distask.dao.controller.filter;

import org.springframework.stereotype.Component;
import ru.serov.distask.dao.controller.model.article.ArticleDTO;
import ru.serov.distask.service.model.FilterParam;

import java.util.List;

@Component
public class ArticleDTOFilter implements IDTOFilter<ArticleDTO> {

    @Override
    public boolean filterDTO(List<FilterParam> conds, ArticleDTO item) {
        return false;
    }
}
