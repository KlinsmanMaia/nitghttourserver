package br.com.nighttour.model.entity;

import javax.persistence.Entity;

@Entity
public class EventCategory extends AbstractEntity{
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
