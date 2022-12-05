package ru.serov.distask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "articles", schema = "company")
public class Article {
    @Id
    @Column(value = "id")
    private Long id;
    @Column(value = "product_id")
    private Long productId;
    @Column(value = "name")
    private String name;
    @Column(value = "content")
    private String content;
    @Column(value = "create_date")
    private Date createDate;
}
