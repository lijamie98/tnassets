package com.telenav.tnassets.data.assets.prod;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telenav.tnassets.data.InstanceEntityMySQL;

@Repository
public interface ProdInstanceRepoMySQL extends CrudRepository<InstanceEntityMySQL, Integer> {
	public List<InstanceEntityMySQL> findAll();

	@Query("SELECT instance from InstanceEntityMySQL instance WHERE instance.date >= :date")
	public List<InstanceEntityMySQL> findByDateAfter(@Param("date") Date date);
	
	@Query("SELECT instance from InstanceEntityMySQL instance WHERE instance.date >= :startDate AND instance.date < :endDate")
	public List<InstanceEntityMySQL> findByDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	public List<InstanceEntityMySQL> findByDate(@Param("date") Date date);
}
