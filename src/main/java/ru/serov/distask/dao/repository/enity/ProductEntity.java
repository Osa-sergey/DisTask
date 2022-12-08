package ru.serov.distask.dao.repository.enity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "products", schema = "company")
public class ProductEntity {
    @Id
    @Column(value = "id")
    private Long id;
    @Column(value = "name")
    private String name;
    @Column(value = "description")
    private String description;
    @Column(value = "implement_cost")
    private Float implementCost;
}
