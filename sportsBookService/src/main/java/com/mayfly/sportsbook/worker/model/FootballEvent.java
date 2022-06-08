package com.mayfly.sportsbook.worker.model;

import javax.validation.constraints.NotBlank;

public class FootballEvent {

	 @NotBlank(message = "Event field cannot empty")
	private String event;
	
	 @NotBlank(message = "Score field cannot empty")
	private String score;
	
	public FootballEvent(String event, String score) {
		setEvent(event);
		setScore(score);
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
}
