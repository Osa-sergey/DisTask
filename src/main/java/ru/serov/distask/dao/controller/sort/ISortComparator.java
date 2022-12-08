package ru.serov.distask.dao.controller.sort;

import java.util.Comparator;
import java.util.List;

public interface ISortComparator<T> {

    Comparator<T> getComparator(String sortedBy, List<String> allowedNames);
}
