package com.andymcsherry.proguarddemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_fragment, null);
	}

	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);

		((TextView) getView().findViewById(R.id.text_view)).setText(String
				.format(getString(R.string.page),
						getArguments().getInt(MainActivity.KEY_PAGE_NUMBER)));
	}
}
