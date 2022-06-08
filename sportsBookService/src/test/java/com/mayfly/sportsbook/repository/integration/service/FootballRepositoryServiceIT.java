package com.mayfly.sportsbook.repository.integration.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.mayfly.sportsbook.config.RedisConfig;
import com.mayfly.sportsbook.exception.PersistenceException;
import com.mayfly.sportsbook.exception.SportbookResourceNotFound;
import com.mayfly.sportsbook.model.FootballEventEntity;
import com.mayfly.sportsbook.repository.FootballEventRepository;
import com.mayfly.sportsbook.repository.service.FootballRepositoryService;



@DirtiesContext
@SpringBootTest(classes= {RedisConfig.class, FootballRepositoryService.class})
@DisplayName("Show Case Integration Tests For FootballRepositoryService Using In Memory Redis DB")
@TestInstance(Lifecycle.PER_CLASS)
public class FootballRepositoryServiceIT {

	@Autowired
	private FootballRepositoryService footballRepositoryService;
	
	@Autowired
	private FootballEventRepository footballEventRepository;
	
	private static final String FB_EVNT_ID = "ENG - NI";
	
	private static final String FB_EVNT_ID_1 = "SCT - NI";
	
	private static final String SCORE = "1-1";
	
	@BeforeEach
	public  void createEntity() {
		footballEventRepository.save(getEntity(FB_EVNT_ID));
		footballEventRepository.save(getEntity(FB_EVNT_ID_1));
	}
	
	
	@Test
	public void testContext() {
		assertNotNull(footballRepositoryService);
	}
	
	/*
	 * Assuming the Before all method pre inserted all the necessary records
	 * Parameterized Testing
	 */
	@ParameterizedTest
	@ValueSource(strings = {FB_EVNT_ID, FB_EVNT_ID})
	public void testFindFootballEventSuccess(String eventId) {
		FootballEventEntity retrvEntity = footballRepositoryService.findByEventId(eventId);
		assertNotNull(retrvEntity);
		assertEquals(SCORE, retrvEntity.getScore());
		assertEquals(eventId, retrvEntity.getId());
	}
	
	/*
	 * Assert Exception message thrown as well.
	 */
	@ParameterizedTest
	@ValueSource(strings = {"dummy", ""})
	public void testFindFootballEventNotFound(String eventId) {
		SportbookResourceNotFound excep = assertThrows(SportbookResourceNotFound.class,() -> footballRepositoryService.findByEventId(eventId));
		//Exception is thrown the 
		assertThat(excep.getMessage()).contains(FootballRepositoryService.ENTITY_NOT_FOUND);

	}
	
	@ParameterizedTest
	@ValueSource(strings = {"GR-LS", "IRL-NI"})
	public void testFootballEventSaveAndUpdate(String eventId) {
		footballRepositoryService.saveFootBallEvent(getEntity(eventId));
		
		FootballEventEntity retrvEntity = footballEventRepository.findById(eventId).get();
		assertNotNull(retrvEntity);
		assertEquals(SCORE, retrvEntity.getScore());
		assertEquals(eventId, retrvEntity.getId());
		
		//Update Scenario
		String updateScore = "3-6";
		retrvEntity.setScore(updateScore);
		footballRepositoryService.saveFootBallEvent(retrvEntity);
		
		retrvEntity = footballEventRepository.findById(eventId).get();
		assertEquals(updateScore, retrvEntity.getScore());
	}
	
	@Test
	public void testFootballSaveFailure() {
		PersistenceException excep = assertThrows(PersistenceException.class,() -> footballRepositoryService.saveFootBallEvent(null));
		
		assertThat(excep.getMessage()).contains(FootballRepositoryService.ENTITY_SAVE_FAILURE);
	}
	
	private static FootballEventEntity getEntity(String eventId) {
		return new FootballEventEntity(eventId, SCORE);
	}
}
