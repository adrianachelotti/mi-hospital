package com.android.mihospital.utils;

import java.util.Calendar;

import android.content.Context;
import android.os.Build;

public abstract class CalendarAPI {
	private static CalendarAPI api;
	protected Context context;

	public static CalendarAPI getAPI() {
		String apiClass;
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			apiClass = "com.android.mihospital.utils.CycleManagerSDK8";
		} else {
			apiClass = "com.android.mihospital.utils.CycleManagerSDK14";
		}

		try {
			Class<? extends CalendarAPI> realClass = Class.forName(apiClass)
					.asSubclass(CalendarAPI.class);
			api = realClass.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

		return api;
	}

	public abstract boolean setAlertOnDevice(Context c,String tittle,String details,Calendar startDate,Calendar endDate, int reminder,String location);

	public void setContext(Context c) {
		context = c;
	}
}