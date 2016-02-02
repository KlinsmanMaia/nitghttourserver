package br.com.nighttour.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Event extends AbstractEntity{
	@NotNull
	private String name;
	private String description;

	@OneToOne
	private EventCategory eventCategory;
	
	@OneToOne
	private Picture cover;
	
	@OneToOne
	private Localization place;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Picture getCover() {
		return cover;
	}
	public void setCover(Picture cover) {
		this.cover = cover;
	}
	public Localization getPlace() {
		return place;
	}
	public void setPlace(Localization place) {
		this.place = place;
	}
	public EventCategory getEventCategory() {
		return eventCategory;
	}
	
	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}
}
