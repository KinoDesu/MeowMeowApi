package dev.kinodesu.MeowMeowApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.kinodesu.MeowMeowApi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
