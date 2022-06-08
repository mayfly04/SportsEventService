package com.mayfly.sportsbook.model;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("FootballEventEntity")
public class FootballEventEntity extends SportEventEntity{

	private static final long serialVersionUID = 1786628373245660762L;
	
	private String score;
	
	public FootballEventEntity(String id, String score) {
		super(id);
		setScore(score);
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	
}
