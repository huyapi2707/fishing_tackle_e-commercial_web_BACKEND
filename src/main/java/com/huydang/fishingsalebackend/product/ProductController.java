package com.huydang.fishingsalebackend.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    @RequestMapping("/get")
    public ResponseEntity<Object> getById(@RequestParam Long id) {
        ProductDTO productDTO = service.getProductById(id);
        if (productDTO != null) {
            return ResponseEntity.ok(productDTO);
        }
        return ResponseEntity.badRequest().body("Invalid product id");
    }

    @RequestMapping("/get_page")
    public ResponseEntity<List<ProductDTO>> getByPage(@RequestParam int page) {
        return ResponseEntity.ok(service.getProductByPage(page));
    }

    @RequestMapping("/sub_category")
    public ResponseEntity<List<ProductDTO>> getBySubtype(@RequestParam int sub_category, @RequestParam int page ) {
        return ResponseEntity.ok(service.getProductBySubtypeId(sub_category, page));
    }
    @RequestMapping("/sub_category_with_limit")
    public ResponseEntity<List<ProductDTO>> getBySubtypeWithLimit(@RequestParam int sub_category, @RequestParam int limit ) {
        return ResponseEntity.ok(service.getProductBySubtypeId(sub_category, 0, limit));
    }

    @RequestMapping("/category")
    public ResponseEntity<List<ProductDTO>> getByCategory(@RequestParam int category, @RequestParam int page ) {
        return ResponseEntity.ok(service.getProductByCategoryId(category, page));
    }

    @RequestMapping("/category_with_limit")
    public ResponseEntity<List<ProductDTO>> getByCategoryWithLimit(@RequestParam int category, @RequestParam int limit ) {
        return ResponseEntity.ok(service.getProductByCategoryId(category, 0, limit));
    }

    @RequestMapping("/brand")
    public ResponseEntity<List<ProductDTO>> getByBrand(@RequestParam int brand, @RequestParam int page ) {
        return ResponseEntity.ok(service.getProductByBrand(brand, page));
    }

    @RequestMapping("/page_amount")
    public ResponseEntity<Integer> getPageTotal() {
        return ResponseEntity.ok(service.countPageTotal());
    }

    @RequestMapping("/category/page_amount")
    public ResponseEntity<Integer> getPageAmountByCategory(@RequestParam int id) {
        return ResponseEntity.ok(service.countPageByCategoryId(id));
    }

    @RequestMapping("/subtype/page_amount")
    public ResponseEntity<Integer> getPageAmountBySubtypeId(@RequestParam int id) {
        return ResponseEntity.ok(service.countPageBySubtypeId(id));
    }

    @RequestMapping("/brand/page_amount")
    public ResponseEntity<Integer> getPageAmountByBrand(@RequestParam int id) {
        return ResponseEntity.ok(service.countPageByBrandId(id));
    }
}
