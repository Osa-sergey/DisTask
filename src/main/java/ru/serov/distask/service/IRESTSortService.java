package ru.serov.distask.service;

import ru.serov.distask.service.model.SortParam;

import java.util.List;

public interface IRESTSortService {

    List<SortParam> getSortParams(String sortedBy, List<String> allowedNames);
}
