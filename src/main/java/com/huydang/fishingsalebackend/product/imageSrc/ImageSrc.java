package com.huydang.fishingsalebackend.product.imageSrc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huydang.fishingsalebackend.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name =  "product_img_src")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageSrc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String src;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
