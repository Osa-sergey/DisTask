package ru.serov.distask.dao.controller.filter;

import ru.serov.distask.exception.impl.ConvertFilterParamException;
import ru.serov.distask.exception.impl.UnacceptableFilterOperationException;
import ru.serov.distask.service.model.FilterParam;

import java.time.LocalDate;
import java.util.List;

public interface IDTOFilter<T> {
    boolean filterDTO(List<FilterParam> conds, T item);

    default boolean checkLongConds(FilterParam cond, Long itemValue) {
        Long condValue;
        try {
            condValue = Long.parseLong(cond.getValue());
        } catch (Exception e) {
            throw new ConvertFilterParamException(cond.getValue());
        }
        switch (cond.getOperation()) {
            case EQ:
                return condValue.equals(itemValue);
            case NEQ:
                return !condValue.equals(itemValue);
            case LE:
                return condValue > itemValue;
            case ELE:
                return condValue >= itemValue;
            case GR:
                return condValue < itemValue;
            case EGR:
                return condValue <= itemValue;
            default:
                throw new UnacceptableFilterOperationException("For LongConds " + cond.getOperation().name()
                        + " not accepted");
        }
    }

    default boolean checkFloatConds(FilterParam cond, Float itemValue) {
        Float condValue;
        try {
            condValue = Float.parseFloat(cond.getValue());
        } catch (Exception e) {
            throw new ConvertFilterParamException(cond.getValue());
        }
        switch (cond.getOperation()) {
            case EQ:
                return condValue.equals(itemValue);
            case NEQ:
                return !condValue.equals(itemValue);
            case LE:
                return condValue > itemValue;
            case ELE:
                return condValue >= itemValue;
            case GR:
                return condValue < itemValue;
            case EGR:
                return condValue <= itemValue;
            default:
                throw new UnacceptableFilterOperationException("For FloatConds " + cond.getOperation().name()
                        + " not accepted");
        }
    }

    default boolean checkLocalDateConds(FilterParam cond, LocalDate itemValue) {
        LocalDate condValue;
        try {
            condValue = LocalDate.parse(cond.getValue());
        } catch (Exception e) {
            throw new ConvertFilterParamException(cond.getValue());
        }
        switch (cond.getOperation()) {
            case EQ:
                return condValue.isEqual(itemValue);
            case NEQ:
                return !condValue.isEqual(itemValue);
            case LE:
                return condValue.isAfter(itemValue);
            case GR:
                return condValue.isBefore(itemValue);
            default:
                throw new UnacceptableFilterOperationException("For LocalDateConds " + cond.getOperation().name()
                        + " not accepted");
        }
    }

    default boolean checkStringConds(FilterParam cond, String itemValue) {
        String condValue = cond.getValue();
        switch (cond.getOperation()) {
            case EQ:
                return condValue.equals(itemValue);
            case NEQ:
                return !condValue.equals(itemValue);
            case CONT:
                return itemValue.contains(condValue);
            default:
                throw new UnacceptableFilterOperationException("For StringConds " + cond.getOperation().name()
                        + " not accepted");
        }
    }
}
