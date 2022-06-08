package com.mayfly.sportsbook.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mayfly.sportsbook.repository.service.FootballRepositoryService;
import com.mayfly.sportsbook.worker.model.FootballEvent;
import com.mayfly.sportsbook.worker.model.FootballEventMapper;

@Component
public class FootBallEventWorker {

	@Autowired
	private FootballRepositoryService footballRepositoryService;
	
	
	public FootballEvent getFootBallEvent(String eventId) {
		return FootballEventMapper.toDomain(footballRepositoryService.findByEventId(eventId));
	}
	
	
	public void upsertFootballEvent(FootballEvent footballEvent) {
		footballRepositoryService.saveFootBallEvent(FootballEventMapper.toEntity(footballEvent));
	}
}
