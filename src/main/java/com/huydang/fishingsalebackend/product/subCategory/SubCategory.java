package com.huydang.fishingsalebackend.product.subCategory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huydang.fishingsalebackend.product.Product;
import com.huydang.fishingsalebackend.product.categories.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category_subtype")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "subtype")
    private List<Product> products = new ArrayList<>();
}
