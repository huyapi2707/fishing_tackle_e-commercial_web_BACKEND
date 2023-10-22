package com.huydang.fishingsalebackend.product.subCategory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/subtype")
@RequiredArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subTypeService;

    @GetMapping("/getAllname")
    public ResponseEntity<List<SubCategoryDTO>> getAllName() {
        return ResponseEntity.ok(subTypeService.getAllName());
    }
}
