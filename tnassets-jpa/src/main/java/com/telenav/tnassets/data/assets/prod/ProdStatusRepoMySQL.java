package com.telenav.tnassets.data.assets.prod;


import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.telenav.tnassets.data.StatusEntity;

@Repository
public interface ProdStatusRepoMySQL extends CrudRepository<StatusEntity, Date> {
	public List<StatusEntity> findAll();

//	@Query("SELECT DISTINCT datetime FROM StatusEntity se WHERE se.status='finished' ORDER by datetime DESC LIMIT 1")
	public StatusEntity findFirstByOrderByDatetimeDesc();
}
