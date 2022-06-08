package com.mayfly.sportsbook.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.mayfly.sportsbook.model.FootballEventEntity;
import com.mayfly.sportsbook.repository.service.FootballRepositoryService;
import com.mayfly.sportsbook.worker.FootBallEventWorker;
import com.mayfly.sportsbook.worker.model.FootballEvent;

@ExtendWith(SpringExtension.class)
@WebFluxTest(PushNotificationController.class)
@Import(FootBallEventWorker.class)
public class PushNotificationControllerTests {

	@Autowired
	private WebTestClient webClient;
	
	@MockBean
	private FootballRepositoryService footballRepositoryService;
	
	private static final String EVT_ID = "test";
	
	@BeforeEach
    void mockResponse() {
        when(footballRepositoryService.findByEventId(EVT_ID))
            .thenReturn(new FootballEventEntity("Test Game", "1-0"));
    }
	
	@Test
	public void testPushNotifications() {
		webClient.get().uri("/notifications/football/{eventId}", EVT_ID)
        .header(HttpHeaders.ACCEPT, "application/json")
        .exchange()
        .expectStatus().isOk()
        .expectBody(FootballEvent.class);
	}
	
}
