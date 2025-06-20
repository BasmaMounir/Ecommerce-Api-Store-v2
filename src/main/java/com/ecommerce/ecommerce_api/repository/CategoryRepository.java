package com.ecommerce.ecommerce_api.repository;

import com.ecommerce.ecommerce_api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);

}
