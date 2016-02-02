package br.com.nighttour.model.entity;

import javax.persistence.Entity;


@Entity
public class Localization extends AbstractEntity{
	private double longitude;
	private double latitude;
    private double zoom;
    
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getZoom() {
		return zoom;
	}
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

}
