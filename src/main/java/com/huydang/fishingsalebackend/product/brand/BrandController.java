package com.huydang.fishingsalebackend.product.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping("getAllName")
    public ResponseEntity<List<BrandDTO>> getAllName() {
        return ResponseEntity.ok(brandService.getAllName());
    }

}
