package dev.kinodesu.MeowMeowApi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.kinodesu.MeowMeowApi.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<Long, ProductModel> {

    ProductModel findProductByProductId(Long id);

}
