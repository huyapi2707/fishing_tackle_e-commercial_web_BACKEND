package com.huydang.fishingsalebackend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    @Query(value = """
      select u from User u inner join RegisterCode c\s
      on u.registerCode.id  = c.id\s
      where c.code = :code \s
      """)
    Optional<User> findByRegisterCode(String code);



}
