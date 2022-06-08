package com.mayfly.sportsbook.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayfly.sportsbook.exception.PersistenceException;
import com.mayfly.sportsbook.exception.SportbookResourceNotFound;
import com.mayfly.sportsbook.model.FootballEventEntity;
import com.mayfly.sportsbook.repository.FootballEventRepository;

@Service
public class FootballRepositoryService {

	public static final String ENTITY_NOT_FOUND = "Football Entity not found";
	
	public static final String ENTITY_SAVE_FAILURE = "Football entity cannot be saved";
	
	@Autowired
	private FootballEventRepository footballEventRepository;
	
	
	public FootballEventEntity findByEventId(String id) {
		return footballEventRepository.findById(id).orElseThrow(() -> new SportbookResourceNotFound(ENTITY_NOT_FOUND));
	}
	
	public FootballEventEntity saveFootBallEvent(FootballEventEntity footballEventEntity) {
		try {
            return footballEventRepository.save(footballEventEntity);
        } catch (Exception e) {
            throw new PersistenceException("Football entity cannot be saved");
        }
	}
	
}
