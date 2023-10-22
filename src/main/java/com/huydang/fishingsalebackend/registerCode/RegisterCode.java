package com.huydang.fishingsalebackend.registerCode;

import com.huydang.fishingsalebackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "register_code")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class RegisterCode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private Date expiredAt;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User refUser;
}
