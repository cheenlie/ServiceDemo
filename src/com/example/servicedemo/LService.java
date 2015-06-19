package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;

public class LService extends Service {
	private static final String TAG = "LService";

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "onbind");
		System.out.println("onbind");
		return null;
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "oncreate");
		System.out.println("oncreate");
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "onstart");
		System.out.println("onstart");
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "ondestoty");
		System.out.println("ondestoty");
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "onUnbind");
		System.out.println("onUnbind");
		return super.onUnbind(intent);
	}

	public String getSystemTime() {
		Time t = new Time();
		t.setToNow();
		return t.toString();
	}

	public class LBinder extends Binder {
		LService getService() {
			return LService.this;
		}
	}
}
