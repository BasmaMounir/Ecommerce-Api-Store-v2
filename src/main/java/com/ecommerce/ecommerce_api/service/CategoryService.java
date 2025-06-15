package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.CategoryDto;
import com.ecommerce.ecommerce_api.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getSpecificCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("This category not found")
        );
    }

    public Category addCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName())){
            throw new IllegalArgumentException("This category already exist!");
        }

        Category category = new Category();

        category.setName(categoryDto.getName());
        category.setSlug(categoryDto.getSlug());
        category.setImage(categoryDto.getImage());

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id ,CategoryDto categoryDto){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (categoryDto.getName() != null && !categoryDto.getName().isBlank()) {
            if (!category.getName().equals(categoryDto.getName()) &&
                    categoryRepository.existsByName(categoryDto.getName())) {
                throw new IllegalArgumentException("Category name already exists!");
            }
            category.setName(categoryDto.getName());
        }
        if (categoryDto.getSlug() != null && !categoryDto.getSlug().isBlank()) {
            category.setSlug(categoryDto.getSlug());
        }

        if (categoryDto.getImage() != null && !categoryDto.getImage().isBlank()) {
            category.setImage(categoryDto.getImage());
        }

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id ){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
         categoryRepository.delete(category);
    }


}
