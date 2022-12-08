package ru.serov.distask.dao.controller.filter;

import ru.serov.distask.service.model.FilterParam;

import java.util.List;

public interface IDTOFilter<T> {
    boolean filterDTO(List<FilterParam> conds, T item);
}
