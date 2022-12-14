package ru.serov.distask.service.impl;

import org.springframework.stereotype.Service;
import ru.serov.distask.exception.impl.IncorrectSortParamsFormat;
import ru.serov.distask.exception.impl.UnavailableSortFieldName;
import ru.serov.distask.exception.impl.UnrecognizedSortParamException;
import ru.serov.distask.service.IRESTSortService;
import ru.serov.distask.service.model.Ordering;
import ru.serov.distask.service.model.SortParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RESTSortService implements IRESTSortService {

    @Override
    public List<SortParam> getSortParams(String sortedBy, List<String> allowedNames) {
        return Arrays.stream(sortedBy.split(","))
                .map(param -> {
                    String[] parts = param.split("\\(", 2);
                    if (parts.length < 2) {
                        throw new IncorrectSortParamsFormat();
                    }
                    String name = parts[1].substring(0, parts[1].length() - 1);
                    if (!allowedNames.contains(name)) {
                        throw new UnavailableSortFieldName(name);
                    }
                    SortParam res;
                    switch (parts[0]) {
                        case "asc":
                            res = new SortParam(name, Ordering.ASC);
                            break;
                        case "desc":
                            res = new SortParam(name, Ordering.DESC);
                            break;
                        default:
                            throw new UnrecognizedSortParamException(parts[0]);
                    }
                    return res;
                })
                .collect(Collectors.toList());
    }


}
