package com.mayfly.sportsbook.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("SportEventEntity")

public class SportEventEntity implements Serializable{

	/*
	 * Idea to create generic sport entity and reuse it for various different sports
	 * Fundamental remains the same, custom variables to extending entity
	 */
	
	private static final long serialVersionUID = -4877881089030304160L;

	@Id
	private String id;
	
	private String eventName;
	
	@CreatedDate
	private LocalDateTime created;
	
	@LastModifiedDate
	private LocalDateTime lastUpdated;
	
	public SportEventEntity(String id) {
		setId(id);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	
}
