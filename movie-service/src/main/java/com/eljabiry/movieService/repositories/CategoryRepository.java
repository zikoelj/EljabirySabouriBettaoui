package com.eljabiry.movieService.repositories;

import com.eljabiry.movieService.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category getCategoryByCategoryId(int categoryId);
}
