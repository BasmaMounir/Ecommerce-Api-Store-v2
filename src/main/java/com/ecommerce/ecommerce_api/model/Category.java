package com.ecommerce.ecommerce_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String slug;
    private String image;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "category",orphanRemoval = true)
    List<SubCategory> subCategories;
}
