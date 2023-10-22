package com.huydang.fishingsalebackend.product.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/getAllName")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/getCategoryAndSubtype")
    public ResponseEntity<List<Category>> getCategoryAndSubtype() {
        return ResponseEntity.ok(categoryService.getCategoryAndSubtype());
    }
}
