package com.eljabiry.movieService.services.abstracts;

import com.eljabiry.movieService.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getall();

    Category getCategoryById(int categoryId);
}
