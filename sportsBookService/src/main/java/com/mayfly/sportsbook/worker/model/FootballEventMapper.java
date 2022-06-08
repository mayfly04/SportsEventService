package com.mayfly.sportsbook.worker.model;

import com.mayfly.sportsbook.model.FootballEventEntity;

/*
 * Domain to entity mappers
 * we can use the mapstruct to make this prettier if needed
 */

public final class FootballEventMapper {

	private FootballEventMapper() {
		
	}
	
	public static FootballEventEntity toEntity(FootballEvent footballEvent) {
		return new FootballEventEntity(footballEvent.getEvent(), footballEvent.getScore());
	}
	
	public static FootballEvent toDomain(FootballEventEntity footballEventEntity) {
		return new FootballEvent(footballEventEntity.getId(), footballEventEntity.getScore());
	}
	
}
