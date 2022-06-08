package com.mayfly.sportsbook.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayfly.sportsbook.exception.SportbookResourceNotFound;
import com.mayfly.sportsbook.model.FootballEventEntity;
import com.mayfly.sportsbook.repository.service.FootballRepositoryService;
import com.mayfly.sportsbook.worker.FootBallEventWorker;
import com.mayfly.sportsbook.worker.model.FootballEvent;

@WebMvcTest()
@ContextConfiguration(classes = { FootBallEventWorker.class, SportsEventController.class,
		SportEventControllerExcpAdvicer.class })
public class SportsEventControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private FootballRepositoryService footballRepositoryService;

	private static final String EVT_ID = "test";

	@BeforeEach
	void mockResponse() {
		when(footballRepositoryService.findByEventId(EVT_ID)).thenReturn(new FootballEventEntity("Test Game", "1-0"));
	}

	@Test
	public void shouldReturnOKResponseOnFindingEvent() throws Exception {
		this.mockMvc.perform(get("/events/{eventId}", EVT_ID)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Test Game")));
	}

	@Test
	public void shouldReturnOKResponseOnSavingEvent() throws Exception {
		when(footballRepositoryService.saveFootBallEvent(Mockito.any())).thenReturn(null);

		mockMvc.perform(post("/events/footBallEvent").contentType("application/json")
				.content(objectMapper.writeValueAsString(getDomain(EVT_ID)))).andExpect(status().isOk())
				.andExpect(content().string(containsString("Football Event Successfully Saved")));

	}

	@Test
	public void shouldReturnBadRequestOnMissingValueOnSave() throws Exception {
		when(footballRepositoryService.saveFootBallEvent(Mockito.any())).thenReturn(null);

		mockMvc.perform(post("/events/footBallEvent").contentType("application/json")
				.content(objectMapper.writeValueAsString(getDomain(null)))).andExpect(status().isBadRequest());

	}

	@Test
	public void shouldReturnResourceNotFoundonUnknownEventId() throws Exception {

		when(footballRepositoryService.findByEventId("dummy")).thenThrow(new SportbookResourceNotFound("test throw"));

		this.mockMvc.perform(get("/events/{eventId}", "dummy")).andDo(print()).andExpect(status().isNotFound())
				.andExpect(content().string(containsString(SportEventControllerExcpAdvicer.EVNT_RESRC_NOT_FOUND)));

	}

	private static FootballEvent getDomain(String eventId) {
		return new FootballEvent(eventId, "0-0");
	}
}
