package com.android.mihospital.dominio;

import com.google.android.gms.maps.model.LatLng;

public class Bounds {
	
	private LatLng southwest;
	private LatLng northeast;
	
	public LatLng getSouthwest() {
		return southwest;
	}
	public void setSouthwest(LatLng southwest) {
		this.southwest = southwest;
	}
	public LatLng getNortheast() {
		return northeast;
	}
	public void setNortheast(LatLng northeast) {
		this.northeast = northeast;
	}
}
