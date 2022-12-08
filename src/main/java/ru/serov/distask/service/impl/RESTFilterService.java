package ru.serov.distask.service.impl;

import org.springframework.stereotype.Service;
import ru.serov.distask.exception.impl.IncorrectFilterParamsFormat;
import ru.serov.distask.exception.impl.UnavailableFilterFieldName;
import ru.serov.distask.exception.impl.UnrecognizedFilterParamException;
import ru.serov.distask.service.IRESTFilterService;
import ru.serov.distask.service.model.FilterParam;
import ru.serov.distask.service.model.Operation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RESTFilterService implements IRESTFilterService {
    @Override
    public List<FilterParam> getFilterParams(String filteredBy, List<String> allowedNames) {
        if (filteredBy == null || filteredBy.isEmpty()) return Collections.emptyList();
        return Arrays.stream(filteredBy.split(","))
                .map(param -> {
                    String[] condValue = param.split("=", 2);
                    String[] fieldCond = condValue[0].split("\\[", 2);
                    if (condValue.length < 2 || fieldCond.length < 2) {
                        throw new IncorrectFilterParamsFormat();
                    }
                    String field = fieldCond[0];
                    String cond = fieldCond[1].substring(0, fieldCond[1].length() - 1);
                    String value = condValue[1];
                    if (!allowedNames.contains(field)) {
                        throw new UnavailableFilterFieldName(field);
                    }
                    FilterParam res;
                    switch (cond) {
                        case "eq":
                            res = new FilterParam(field, Operation.EQ, value);
                            break;
                        case "neq":
                            res = new FilterParam(field, Operation.NEQ, value);
                            break;
                        case "gr":
                            res = new FilterParam(field, Operation.GR, value);
                            break;
                        case "egr":
                            res = new FilterParam(field, Operation.EGR, value);
                            break;
                        case "le":
                            res = new FilterParam(field, Operation.LE, value);
                            break;
                        case "ele":
                            res = new FilterParam(field, Operation.ELE, value);
                            break;
                        case "cont":
                            res = new FilterParam(field, Operation.CONT, value);
                            break;
                        default:
                            throw new UnrecognizedFilterParamException(cond);
                    }
                    return res;
                })
                .collect(Collectors.toList());
    }
}
