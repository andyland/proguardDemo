package com.andymcsherry.proguarddemo;

import android.hardware.Sensor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SensorListFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.sensor_list, null);
	}

	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		setListAdapter(new SensorAdapter(getActivity()));
	}
	
	@Override
	public void onListItemClick (ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Sensor sensor = (Sensor) l.getAdapter().getItem(position);
		
		SensorData data = new SensorData();
		data.name = sensor.getName();
		data.delay = sensor.getMinDelay();
		data.power = sensor.getPower();
		data.range = sensor.getMaximumRange();
		data.type = sensor.getType();
		data.vendor = sensor.getVendor();
		data.version = sensor.getVersion();
		
		Bundle b = new Bundle();
		b.putSerializable(SensorDescriptionFragment.KEY_SENSOR_DATA, data);
		
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		/*
		 * Make sure to save fragments, they're often loaded dynamically
		 */
		Fragment fragment = Fragment.instantiate(getActivity(),"com.andymcsherry.proguarddemo.SensorDescriptionFragment");
		fragment.setArguments(b);
		transaction.replace(R.id.fragment_container, fragment);
		transaction.addToBackStack("backstack");
		transaction.commit();
	}
}
