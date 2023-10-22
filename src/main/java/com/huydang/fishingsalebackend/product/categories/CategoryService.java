package com.huydang.fishingsalebackend.product.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllCategory() {
        return categoryRepository.findAll().stream().map(categoryMapper).collect(Collectors.toList());
    }

    public List<Category> getCategoryAndSubtype() {
        return categoryRepository.findAll();
    }

    public  void addNewCategory(Category newCategory) {
        categoryRepository.save(newCategory);
    }
}
