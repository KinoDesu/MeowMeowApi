package dev.kinodesu.MeowMeowApi.repository;

import dev.kinodesu.MeowMeowApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByOrderByIdDesc();
    Page<Product> findAll(Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.details pd WHERE KEY(pd) = :detailKey AND VALUE(pd) LIKE %:value% AND p.price between :min and :max")
    List<Product> findProductByFilters(String detailKey, String value, Double min, Double max);
}
