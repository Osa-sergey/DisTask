package ru.serov.distask.service;

import ru.serov.distask.dao.controller.model.article.ArticleDTO;
import ru.serov.distask.service.model.SortParam;

import java.util.Comparator;
import java.util.List;

public interface IRESTSortService {
    Comparator<ArticleDTO> getComparator(String sortedBy);

    List<SortParam> getSortParams(String sortedBy, List<String> allowedNames);
}
