package com.huydang.fishingsalebackend.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByToken(String token);

    @Query(value = """
      select t from JwtToken t inner join User u\s
      on t.referenceUser.id  = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<JwtToken> findAllByReferenceUserId(Long id);
}
