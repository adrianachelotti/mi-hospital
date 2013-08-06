package com.android.mihospital.utils;

import java.util.Calendar;



import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import android.net.Uri;
import android.view.Gravity;
import android.widget.Toast;

public class CycleManagerSDK8 extends CalendarAPI {
	private Context context;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public boolean setAlertOnDevice(Context c,String tittle,String details,Calendar startDate,Calendar endDate, int reminder,String location) {
		context=c;


		Uri EVENTS_URI = Uri
				.parse("content://com.android.calendar/" + "events");

		Uri REMINDERS_URI = Uri.parse("content://com.android.calendar/"
				+ "reminders");

		ContentResolver cr = context.getContentResolver();

		ContentValues values = new ContentValues();
		values.put("calendar_id", 1);
		values.put("title", tittle);
		values.put("description", details);
		values.put("dtstart", startDate.getTimeInMillis());
		values.put("dtend", endDate.getTimeInMillis());
		values.put("hasAlarm", 1);
		Uri event = cr.insert(EVENTS_URI, values);

		values = new ContentValues();
		values.put("event_id", Long.parseLong(event.getLastPathSegment()));
		values.put("method", 1);
		values.put("minutes", reminder);
		cr.insert(REMINDERS_URI, values);
		
		Toast toast = Toast.makeText(context,
				"Evento creado", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();	 
 	
		return true;
	}
}