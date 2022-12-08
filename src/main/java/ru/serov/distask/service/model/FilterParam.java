package ru.serov.distask.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterParam {
    private String fieldName;
    private Operation operation;
    private String value;
}
