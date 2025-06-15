package com.ecommerce.ecommerce_api.repository;

import com.ecommerce.ecommerce_api.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    boolean existsByName(String name);
    List<SubCategory> findAllByCategoryId(Long categoryId);

}
