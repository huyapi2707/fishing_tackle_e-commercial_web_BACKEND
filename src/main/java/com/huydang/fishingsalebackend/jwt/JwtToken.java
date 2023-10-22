package com.huydang.fishingsalebackend.jwt;

import com.huydang.fishingsalebackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "jwt_tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private boolean expired;
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User referenceUser;
}
