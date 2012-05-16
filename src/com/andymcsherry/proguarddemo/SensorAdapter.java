package com.andymcsherry.proguarddemo;

import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SensorAdapter extends BaseAdapter {
	
	private Activity mActivity;
	List<Sensor> mSensors;
	
	public SensorAdapter(Activity activity) {
		mActivity = activity;
		mSensors = ((SensorManager)mActivity.getSystemService(Service.SENSOR_SERVICE)).getSensorList(Sensor.TYPE_ALL);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView v = new TextView(mActivity);
		
		v.setText(mSensors.get(position).getName());
		v.setTextSize(22);
		v.setPadding(Util.getXPixels(mActivity, 5),
				Util.getYPixels(mActivity, 2),
				Util.getXPixels(mActivity, 5),
				Util.getYPixels(mActivity, 2));
		
		return v;
	}

	public long getItemId(int position) {
		return position;
	}

	public Object getItem(int position) {
		return mSensors.get(position);
	}

	public int getCount() {
		return mSensors.size();
	}
}
