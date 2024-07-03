package dev.kinodesu.MeowMeowApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.kinodesu.MeowMeowApi.model.stock.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
