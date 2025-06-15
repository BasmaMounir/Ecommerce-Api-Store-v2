package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.SubCategoryDto;
import com.ecommerce.ecommerce_api.model.SubCategory;
import com.ecommerce.ecommerce_api.response.ApiResponse;
import com.ecommerce.ecommerce_api.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subcategories")

public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllSubCategories() {
        List<SubCategoryDto> categories = subCategoryService.getAllSubCategories();
        return ResponseEntity.ok(new ApiResponse(
                categories.size(), true,
                "Sub Categories retrieved successfully", categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSpecificSubCategory(@PathVariable Long id) {
        SubCategory subCategory  = subCategoryService.getSpecificSubCategory(id);
        if (subCategory != null) {
            return ResponseEntity.ok(subCategory);
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getSubCategoriesByCategory(@PathVariable Long categoryId) {
        List<SubCategory> subCategories = subCategoryService.getAllSubCategoriesOnCategory(categoryId);
        return ResponseEntity.ok(new ApiResponse<>(subCategories.size(),true, "Subcategories retrieved", subCategories));
    }


    @PostMapping
    public ResponseEntity<SubCategory> addCategory(@RequestBody SubCategoryDto dto) {
        SubCategory subCategory  = subCategoryService.addSubCategory(dto);
        return ResponseEntity.ok(subCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateCategory(@PathVariable Long id, @RequestBody SubCategoryDto dto) {
        SubCategory subCategory  = subCategoryService.updateSubCategory(id,dto);
        return ResponseEntity.ok(subCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubCategory> deleteCategory(@PathVariable Long id) {
        subCategoryService.deleteSubCategory(id);
        return ResponseEntity.noContent().build();
    }

}
