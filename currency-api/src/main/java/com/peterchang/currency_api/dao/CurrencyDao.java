package com.peterchang.currency_api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.peterchang.currency_api.entity.CurrencyEntity;

@Repository
public interface CurrencyDao extends JpaRepository<CurrencyEntity, String> {
	
	@Query(value = "SELECT * FROM CURRENCY WHERE (:id = 'ALL' OR CURRENCYID = :id)", nativeQuery = true)
	List<CurrencyEntity> findByCurrenyIdOrAll(@Param("id") String name);

}
