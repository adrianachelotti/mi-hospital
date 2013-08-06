package com.android.mihospital.dominio;

import com.google.android.gms.maps.model.LatLng;

public class Step {
	private LatLng startLocation;
	private LatLng endLocation;
	private String travelMode;
	private StringPolyline polyline;
	public LatLng getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(LatLng startLocation) {
		this.startLocation = startLocation;
	}
	public LatLng getEndLocation() {
		return endLocation;
	}
	public void setEndLocation(LatLng endLocation) {
		this.endLocation = endLocation;
	}
	public String getTravelMode() {
		return travelMode;
	}
	public void setTravelMode(String travelMode) {
		this.travelMode = travelMode;
	}
	public StringPolyline getPolyline() {
		return polyline;
	}
	public void setPolyline(StringPolyline polyline) {
		this.polyline = polyline;
	}
}
