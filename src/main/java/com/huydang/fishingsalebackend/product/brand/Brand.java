package com.huydang.fishingsalebackend.product.brand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huydang.fishingsalebackend.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_brand")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private List<Product>  products = new ArrayList<>();
}
