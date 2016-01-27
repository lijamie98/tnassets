package com.telenav.tnassets.data.assets.prod;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telenav.tnassets.data.SpotInstanceEntity;

@Repository
public interface ProdSpotInstanceRepo extends CrudRepository<SpotInstanceEntity, Integer> {
	public List<SpotInstanceEntity> findAll();

	@Query("SELECT instance from SpotInstanceEntity instance WHERE instance.date >= :date")
	public List<SpotInstanceEntity> findByDateAfter(@Param("date") Date date);
	
	@Query("SELECT instance from SpotInstanceEntity instance WHERE instance.date >= :startDate AND instance.date < :endDate")
	public List<SpotInstanceEntity> findByDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	public List<SpotInstanceEntity> findByDate(@Param("date") Date date);
}
