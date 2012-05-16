package com.andymcsherry.proguarddemo;

import android.os.AsyncTask;
import android.util.Log;

public class BackgroundTask extends AsyncTask<Void, Integer, Void> {
	
	@Override
	protected Void doInBackground(Void... params) {
		try {
			for (int i = 0; i < 25; i++) {
				Thread.sleep(100);
				publishProgress(new Integer[] {i * 4});
			}
		} catch (InterruptedException e) {
			Log.e("Demo", "Thread interrupted", e);
			Thread.currentThread().interrupt();
		}
		return null;
	}
}
