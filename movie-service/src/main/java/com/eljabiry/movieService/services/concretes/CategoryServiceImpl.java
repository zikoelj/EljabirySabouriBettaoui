package com.eljabiry.movieService.services.concretes;

import com.eljabiry.movieService.services.abstracts.CategoryService;
import com.eljabiry.movieService.repositories.CategoryRepository;
import com.eljabiry.movieService.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Cacheable(value = "categories")
    @Override
    public List<Category> getall() {

        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryRepository.getCategoryByCategoryId(categoryId);
    }
}
