package com.android.mihospital.utils;

import java.lang.reflect.Type;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class LatLngDeserializer implements JsonDeserializer<LatLng>{
		

	public LatLng deserialize(JsonElement json, Type type,
			JsonDeserializationContext arg) throws JsonParseException {
		JsonObject jobject = (JsonObject) json;

	     return new LatLng(
	             jobject.get("lat").getAsFloat(), 
	             jobject.get("lng").getAsFloat());
	}
}
