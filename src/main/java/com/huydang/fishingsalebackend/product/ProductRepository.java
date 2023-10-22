package com.huydang.fishingsalebackend.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.subtype.id = :id")
    List<Product> findAllBySubtypeId(int id, Pageable page);




    @Query("select p from Product p where p.subtype.category.id = :id")
    List<Product> findAllByCategoryId(int id, Pageable page);



    @Query("select p from Product p where p.brand.id = :id")
    List<Product> findAllByBrandId(int id, Pageable page);

    @Query("select count(*) from Product p")
    int countAll();

    @Query("select count(*) from Product p where p.subtype.category.id  = :id")
    int countAllByCategoryId(int id);

    @Query("select count(*) from Product p where p.subtype.id = :id")
    int countAllBySubtypeId(int id);

    @Query("select count(*) from Product p where p.brand.id = :id")
    int countAllByBrandId(int id);
}
