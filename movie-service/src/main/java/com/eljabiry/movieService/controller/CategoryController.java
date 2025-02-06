package com.eljabiry.movieService.controller;

import com.eljabiry.movieService.services.abstracts.CategoryService;
import com.eljabiry.movieService.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/categories/")
@RequiredArgsConstructor
//@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("getall")
    public List<Category> getall() {
       return categoryService.getall();
    }
}
