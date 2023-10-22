package com.huydang.fishingsalebackend.product.cart;

import com.huydang.fishingsalebackend.product.type.Type;
import com.huydang.fishingsalebackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cart {
    @Id
    @GeneratedValue(generator = "cart-generator")
    @GenericGenerator(name = "cart-generator",
            parameters = @Parameter(name = "prefix", value = "cart"),
            strategy = "com.huydang.fishingsalebackend.config.IdentifierGenerator")
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "paid")
    private Boolean paid;
}
