package com.huydang.fishingsalebackend.product;


import com.huydang.fishingsalebackend.product.description.Description;
import com.huydang.fishingsalebackend.product.imageSrc.ImageSrc;
import com.huydang.fishingsalebackend.product.type.Type;
import com.huydang.fishingsalebackend.product.brand.Brand;
import com.huydang.fishingsalebackend.product.subCategory.SubCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Long rating;
    @ManyToOne
    @JoinColumn(name = "subtype")
    private SubCategory subtype;


    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Description> descriptions = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ImageSrc> imageSrcs = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Type> types = new ArrayList<>();
}
