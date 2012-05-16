package com.andymcsherry.proguarddemo;

import android.hardware.Sensor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SensorDescriptionFragment extends Fragment {
	
	public static final String KEY_SENSOR_DATA = "sensor_data";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.sensor_description, null);
	}

	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		updateFields((SensorData) getArguments().getSerializable(KEY_SENSOR_DATA));
	}

	@SuppressWarnings("deprecation")
	private void updateFields(SensorData sensorData) {
		((TextView)getView().findViewById(R.id.sensor_name)).setText(sensorData.name);
		((TextView)getView().findViewById(R.id.version)).setText(String.valueOf(sensorData.version));
		((TextView)getView().findViewById(R.id.vendor_name)).setText(sensorData.vendor);
		((TextView)getView().findViewById(R.id.power)).setText(String.valueOf(sensorData.power));
		((TextView)getView().findViewById(R.id.range)).setText(String.valueOf(sensorData.range));
		((TextView)getView().findViewById(R.id.delay)).setText(String.valueOf(sensorData.delay));
		
		TextView type = (TextView)getView().findViewById(R.id.type);
		
		switch (sensorData.type) {
		case Sensor.TYPE_ACCELEROMETER:
			type.setText("Accelerometer");
		case Sensor.TYPE_AMBIENT_TEMPERATURE:
			type.setText("Ambient Temperature");
		case Sensor.TYPE_GRAVITY:
			type.setText("Gravity");
		case Sensor.TYPE_GYROSCOPE:
			type.setText("Gyroscope");
		case Sensor.TYPE_LIGHT:
			type.setText("Light");
		case Sensor.TYPE_LINEAR_ACCELERATION:
			type.setText("Linear Acceleration");
		case Sensor.TYPE_MAGNETIC_FIELD:
			type.setText("Mangetic Field");
		case Sensor.TYPE_ORIENTATION:
			type.setText("Orientation");
		case Sensor.TYPE_PRESSURE:
			type.setText("Pressure");
		case Sensor.TYPE_PROXIMITY:
			type.setText("Proximity");
		case Sensor.TYPE_RELATIVE_HUMIDITY:
			type.setText("Relative Humidity");
		case Sensor.TYPE_ROTATION_VECTOR:
			type.setText("Rotation Vector");
		case Sensor.TYPE_TEMPERATURE:
			type.setText("Temperature");
		default:
			type.setText("Unknown");
		}
	}
}
