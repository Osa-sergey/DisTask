package ru.serov.distask.dao.controller.filter;

import org.springframework.stereotype.Component;
import ru.serov.distask.dao.controller.model.product.ProductDTO;
import ru.serov.distask.service.model.FilterParam;

import java.util.List;

@Component
public class ProductDTOFilter implements IDTOFilter<ProductDTO> {
    @Override
    public boolean filterDTO(List<FilterParam> conds, ProductDTO item) {
        int failsCount = 0;
        for (FilterParam cond : conds) {
            switch (cond.getFieldName()) {
                case "id":
                    if (!checkLongConds(cond, item.getId())) failsCount++;
                    break;
                case "name":
                    if (!checkStringConds(cond, item.getName())) failsCount++;
                    break;
                case "description":
                    if (!checkStringConds(cond, item.getDescription())) failsCount++;
                    break;
                case "implement_cost":
                    if (!checkFloatConds(cond, item.getImplement_cost())) failsCount++;
                    break;
            }
            if (failsCount > 0) return false;
        }
        return true;
    }
}