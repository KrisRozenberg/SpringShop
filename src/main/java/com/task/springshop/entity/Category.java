package com.task.springshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(nullable = false, length = 45)
    private String name;

    @ManyToOne
    @JoinColumn(name="parent_category_id")
    private Category parentCategory;

    @JsonIgnore
    @OneToMany(mappedBy="parentCategory")
    private Set<Category> categories;

    public Category(String name, Set<Category> categories) {
        this.name = name;
        this.categories = categories;
    }

    public Category(String name, Category parentCategory, Set<Category> categories) {
        this.name = name;
        this.parentCategory = parentCategory;
        this.categories = categories;
    }
}
