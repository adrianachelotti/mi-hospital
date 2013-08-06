package com.android.mihospital.utils;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.ContentResolver;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;

@SuppressLint("NewApi")
public class CycleManagerSDK14 extends CalendarAPI{
	 
	@Override
	public boolean setAlertOnDevice(Context c,String tittle,String details,Calendar startDate,Calendar endDate, int reminder,String location) {
		context=c;

		ContentResolver cr = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, startDate.getTimeInMillis());
		values.put(Events.DTEND, endDate.getTimeInMillis());
		values.put(Events.TITLE, tittle);
		values.put(Events.DESCRIPTION, details);
		values.put(Events.CALENDAR_ID, 1);
		values.put(Events.EVENT_LOCATION,location);
		values.put(Events.EVENT_TIMEZONE, "America/Argentina/Buenos_Aires");
		Uri uri = cr.insert(Events.CONTENT_URI, values);
		
		long eventID = Long.parseLong(uri.getLastPathSegment());
		
		ContentValues rmValues = new ContentValues();
		rmValues.put(Reminders.MINUTES, reminder);
		rmValues.put(Reminders.EVENT_ID, eventID);
		rmValues.put(Reminders.METHOD, Reminders.METHOD_ALERT);

		Intent intent = new Intent(Intent.ACTION_EDIT).setData(uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
		intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate.getTimeInMillis());
		intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate.getTimeInMillis());
		context.startActivity(intent);
		return true;
	}

}
