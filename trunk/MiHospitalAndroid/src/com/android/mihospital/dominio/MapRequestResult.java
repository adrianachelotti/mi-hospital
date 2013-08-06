package com.android.mihospital.dominio;

import java.util.List;

public class MapRequestResult {

	private List<Route> routes;
	private String status;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

}
