package com.mayfly.sportsbook.worker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mayfly.sportsbook.model.FootballEventEntity;
import com.mayfly.sportsbook.repository.service.FootballRepositoryService;
import com.mayfly.sportsbook.worker.model.FootballEvent;

@ExtendWith({MockitoExtension.class})
@DisplayName("Add Unit test cases FootBallEventWorker")
public class FootBallEventWorkerTests {

	private EasyRandom generator = new EasyRandom();
	@Mock
	private FootballRepositoryService footballRepositoryService;
	
	@InjectMocks
	private FootBallEventWorker footballEventWorker;
	
	@Test
	public void testGetFootBallEvent() {
		when(footballRepositoryService.findByEventId(Mockito.anyString())).thenReturn(generator.nextObject(FootballEventEntity.class));
		FootballEvent footballEvent  = footballEventWorker.getFootBallEvent("test");
		assertNotNull(footballEvent);
		assertTrue(StringUtils.isNotBlank(footballEvent.getEvent()));
		assertTrue(StringUtils.isNotBlank(footballEvent.getScore()));
		
	}
	
	@Test
	public void testUpsertFootBallEvent() {
		when(footballRepositoryService.saveFootBallEvent(Mockito.any())).thenReturn(generator.nextObject(FootballEventEntity.class));
		footballEventWorker.upsertFootballEvent(generator.nextObject(FootballEvent.class));
		
	}
	
}
