package com.android.mihospital.dominio;

import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class Leg {
	private LatLng startLocation;
	private LatLng endLocation;
	private String startAddress;
	private String endAddress;
	private List<Step> steps;
	
	public LatLng getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(LatLng startLocaltion) {
		this.startLocation = startLocaltion;
	}
	public LatLng getEndLocation() {
		return endLocation;
	}
	public void setEndLocation(LatLng endLocaltion) {
		this.endLocation = endLocaltion;
	}
	public List<Step> getSteps() {
		return steps;
	}
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	
}
