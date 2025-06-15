package com.ecommerce.ecommerce_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubCategoryDto {
    private Long id;
    private String name;
    private String slug;
    private Long categoryId;
    private LocalDateTime createdAt;
}