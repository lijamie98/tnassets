package com.telenav.tnassets.data.assets.prod;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telenav.tnassets.data.ReservedEntity;

@Repository
public interface ProdReservedRepo extends CrudRepository<ReservedEntity, Integer>  {
	
	@Query("SELECT res from ReservedEntity res WHERE res.date >= :date AND res.state = 'active'")
	public List<ReservedEntity> findByDateAfter(@Param("date") Date date);

	public List<ReservedEntity> findByDate(@Param("date") Date date);
}
