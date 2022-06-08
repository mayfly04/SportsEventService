package com.mayfly.sportsbook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mayfly.sportsbook.model.FootballEventEntity;

@Repository
public interface FootballEventRepository extends CrudRepository<FootballEventEntity, String> {
	
	

}
