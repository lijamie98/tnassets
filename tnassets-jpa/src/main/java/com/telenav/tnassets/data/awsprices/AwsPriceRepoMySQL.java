package com.telenav.tnassets.data.awsprices;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AwsPriceRepoMySQL extends CrudRepository<AwsPriceEntityMySQL, Integer> {
	public List<AwsPriceEntityMySQL> findAll();

	@Query("SELECT price from AwsPriceEntityMySQL price WHERE price.datetime >= :datetime")
	public List<AwsPriceEntityMySQL> findByDateAfter(@Param("datetime") Date datetime);
	
	public List<AwsPriceEntityMySQL> findByDatetime(@Param("datetime") Date datetime);
}