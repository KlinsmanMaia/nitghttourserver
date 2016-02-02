package br.com.nighttour.model.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;


@Entity
public class Picture extends AbstractEntity{

	@Lob
	private byte[] picture;
    
	private Long timestamp;

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
