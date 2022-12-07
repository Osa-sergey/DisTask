package ru.serov.distask.dao.controller.sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.serov.distask.dao.controller.model.product.ProductDTO;
import ru.serov.distask.service.IRESTSortService;
import ru.serov.distask.service.model.Ordering;
import ru.serov.distask.service.model.SortParam;

import java.util.Comparator;
import java.util.List;

@Component
public class ProductDTOSortComparator implements ISortComparator<ProductDTO> {
    private final IRESTSortService sortService;

    @Autowired
    public ProductDTOSortComparator(IRESTSortService sortService) {
        this.sortService = sortService;
    }

    @Override
    public Comparator<ProductDTO> getComparator(String sortedBy, List<String> allowedNames) {
        Comparator<ProductDTO> comparator = (a1, a2) -> 0;
        if (sortedBy == null || sortedBy.isEmpty()) return comparator;
        List<SortParam> params = sortService.getSortParams(sortedBy, allowedNames);
        for (SortParam param : params) {
            switch (param.getName()) {
                case "id":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ProductDTO::getId, Comparator.reverseOrder())
                            : comparator.thenComparing(ProductDTO::getId);
                    break;
                case "name":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ProductDTO::getName, Comparator.reverseOrder())
                            : comparator.thenComparing(ProductDTO::getName);
                    break;
                case "description":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ProductDTO::getDescription, Comparator.reverseOrder())
                            : comparator.thenComparing(ProductDTO::getDescription);
                    break;
                case "implement_cost":
                    comparator = (param.getOrder() == Ordering.DESC) ?
                            comparator.thenComparing(ProductDTO::getImplement_cost, Comparator.reverseOrder())
                            : comparator.thenComparing(ProductDTO::getImplement_cost);
                    break;
            }
        }
        return comparator;
    }
}
