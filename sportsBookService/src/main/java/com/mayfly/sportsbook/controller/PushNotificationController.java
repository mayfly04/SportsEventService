package com.mayfly.sportsbook.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayfly.sportsbook.worker.FootBallEventWorker;
import com.mayfly.sportsbook.worker.model.FootballEvent;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/notifications")
public class PushNotificationController {

	@Autowired
	private FootBallEventWorker footBallEventWorker;
	
	//Can be moved to properties file for more control
	private static final int NOTIFICATION_INTERVAL = 5;

	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(value = "/football/{eventId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<FootballEvent> getResourceUsage(@PathVariable String eventId) {
		return Flux.interval(Duration.ofSeconds(NOTIFICATION_INTERVAL))
				   .map(fluxEvent -> footBallEventWorker.getFootBallEvent(eventId));

	}
}