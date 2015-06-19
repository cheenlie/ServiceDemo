package com.example.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//参考链接地址： http://www.jb51.net/article/35812.htm
public class MainActivity extends Activity implements  android.view.View.OnClickListener {

	private LService mLService;
	private TextView mTextView;
	private Button startServiceButton;
	private Button stopServiceButton;
	private Button bindServiceButton;
	private Button unbindServiceButton;
	private Context mContext;
	
	//这里需要用到ServiceConnection，在Context.bindService和context.unBindService()里用到
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		// 当bindService时，让TextView显示LService里getSystemTime()方法的返回值
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mLService = ((LService.LBinder) service).getService();
			mTextView.setText("I am from Service :" + mLService.getSystemTime());
		}

		public void onServiceDisconnected(ComponentName name) {
		}
	};

	public void setupViews() {
		mContext = MainActivity.this;
		mTextView = (TextView) findViewById(R.id.text);
		startServiceButton = (Button) findViewById(R.id.startservice);
		stopServiceButton = (Button) findViewById(R.id.stopservice);
		bindServiceButton = (Button) findViewById(R.id.bindservice);
		unbindServiceButton = (Button) findViewById(R.id.unbindservice);
		startServiceButton.setOnClickListener(this);
		stopServiceButton.setOnClickListener(this);
		bindServiceButton.setOnClickListener(this);
		unbindServiceButton.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupViews();
	}

	public void onClick(View v) {
		if (v == startServiceButton) {
			Intent i = new Intent(MainActivity.this, LService.class);
			mContext.startService(i);
		} else if (v == stopServiceButton) {
			Intent i = new Intent(MainActivity.this, LService.class);
			mContext.stopService(i);
		} else if (v == bindServiceButton) {
			Intent i = new Intent(MainActivity.this, LService.class);
			mContext.bindService(i, mServiceConnection, BIND_AUTO_CREATE);
		} else {
			mContext.unbindService(mServiceConnection);
		}
	}

}
