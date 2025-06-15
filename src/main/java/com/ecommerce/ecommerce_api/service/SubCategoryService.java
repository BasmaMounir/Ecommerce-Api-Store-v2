package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.SubCategoryDto;
import com.ecommerce.ecommerce_api.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.model.SubCategory;
import com.ecommerce.ecommerce_api.repository.CategoryRepository;
import com.ecommerce.ecommerce_api.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

//   public List<SubCategory>getAllSubCategories(){
//       return subCategoryRepository.findAll();
//   }

    public List<SubCategoryDto> getAllSubCategories() {
        List<SubCategory> subCategories = subCategoryRepository.findAll();

        return subCategories.stream().map(sub -> {
            SubCategoryDto dto = new SubCategoryDto();
            dto.setId(sub.getId());
            dto.setName(sub.getName());
            dto.setSlug(sub.getSlug());
            dto.setCreatedAt(sub.getCreatedAt());
            dto.setCategoryId(sub.getCategory().getId());
            return dto;
        }).collect(Collectors.toList());
    }


    public SubCategory getSpecificSubCategory(Long id) {
        return subCategoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("This sub category not found"));
    }

    public List<SubCategory> getAllSubCategoriesOnCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found");
        }

        return subCategoryRepository.findAllByCategoryId(categoryId);
    }


    public SubCategory addSubCategory(SubCategoryDto dto) {
        if (subCategoryRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Name already exists!");
        }

        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found!")
        );

        SubCategory subCategory = new SubCategory();
        subCategory.setName(dto.getName());
        subCategory.setSlug(dto.getSlug());
        subCategory.setCategory(category);
        subCategory.setCreatedAt(LocalDateTime.now());
        subCategory.setUpdatedAt(LocalDateTime.now());

        return subCategoryRepository.save(subCategory);
    }

    public void deleteSubCategory(Long id ){
        SubCategory subCategory = getSpecificSubCategory(id);
        subCategoryRepository.delete(subCategory);
    }

    public SubCategory updateSubCategory(Long id ,SubCategoryDto dto){
        SubCategory subCategory = getSpecificSubCategory(id);

        if (dto.getName() != null && !dto.getName().isBlank()) {
            if (!subCategory.getName().equals(dto.getName()) &&
                    categoryRepository.existsByName(dto.getName())) {
                throw new IllegalArgumentException("Category name already exists!");
            }
            subCategory.setName(dto.getName());
        }
        if (dto.getSlug() != null && !dto.getSlug().isBlank()) {
            subCategory.setSlug(dto.getSlug());
        }
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            subCategory.setCategory(category);
        }

        subCategory.setUpdatedAt(LocalDateTime.now());

        return subCategoryRepository.save(subCategory);
    }

}
