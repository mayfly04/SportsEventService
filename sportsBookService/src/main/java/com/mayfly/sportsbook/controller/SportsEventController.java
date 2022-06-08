package com.mayfly.sportsbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayfly.sportsbook.worker.FootBallEventWorker;
import com.mayfly.sportsbook.worker.model.FootballEvent;

@RestController
@RequestMapping("/events")
public class SportsEventController {
	
	@Autowired
	private FootBallEventWorker footBallEventWorker;

	@GetMapping(value = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FootballEvent getFootBallEventById(@PathVariable String eventId) {
		return footBallEventWorker.getFootBallEvent(eventId);
	}
	
	@PostMapping( value = "/footBallEvent", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public String saveFootballEvent(@Valid @RequestBody FootballEvent footballEvent) {
		footBallEventWorker.upsertFootballEvent(footballEvent);
		return "Football Event Successfully Saved";
	}
	
}
