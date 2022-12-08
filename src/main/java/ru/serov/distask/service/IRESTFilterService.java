package ru.serov.distask.service;

import ru.serov.distask.service.model.FilterParam;

import java.util.List;

public interface IRESTFilterService {
    List<FilterParam> getFilterParams(String filteredBy, List<String> allowedNames);
}
