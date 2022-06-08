package com.mayfly.sportsbook.repository.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mayfly.sportsbook.exception.PersistenceException;
import com.mayfly.sportsbook.exception.SportbookResourceNotFound;
import com.mayfly.sportsbook.model.FootballEventEntity;
import com.mayfly.sportsbook.repository.FootballEventRepository;



@ExtendWith({MockitoExtension.class})
@DisplayName("Add Unit test cases FootballRepositoryService")
public class FootballRepositoryServiceTests {
	
	@Mock
	private FootballEventRepository footballEventRepository;
	
	@InjectMocks
	private FootballRepositoryService footballRepositoryService;
	
	/*
	 * Just to show case the kind of assert I would normally do
	 * We can organize more by creating static classes to create and populate dummy test objects
	 * create static variables for expected values to assert against
	 */
	@Test
	public void testFootballEventRetrieveAndFound() {
		when(footballEventRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new FootballEventEntity("x", "y")));
		FootballEventEntity retrievedEntity = footballRepositoryService.findByEventId("test");
		assertNotNull(retrievedEntity);
		assertEquals("x", retrievedEntity.getId());
		assertTrue(StringUtils.isNotBlank(retrievedEntity.getScore()));
	}
	
	/*
	 * Test cases are not complete without error scenarios
	 * I would always the error scenarios first
	 */
	@Test
	public void testFootballEventRetrieveAndNotFound() {
		when(footballEventRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		assertThrows(SportbookResourceNotFound.class,() -> footballRepositoryService.findByEventId("test"));
	}
	
	
	@Test
	public void testFootballEventSaveSuccess() {
		when(footballEventRepository.save(Mockito.any())).thenReturn(new FootballEventEntity("saved","created"));
		FootballEventEntity footballEventEntity = footballRepositoryService.saveFootBallEvent(new FootballEventEntity("", ""));
		assertNotNull(footballEventEntity);
		assertEquals("saved", footballEventEntity.getId());
		assertEquals("created", footballEventEntity.getScore());
	}
	
	
	@Test
	public void testFootballEventSaveFailure() {
		when(footballEventRepository.save(Mockito.any())).thenThrow(new RuntimeException());
		assertThrows(PersistenceException.class,() -> footballRepositoryService.saveFootBallEvent(new FootballEventEntity("", "")));
	}
	
}
