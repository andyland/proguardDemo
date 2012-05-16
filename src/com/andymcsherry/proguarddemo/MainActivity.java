package com.andymcsherry.proguarddemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MainActivity extends FragmentActivity {

	private BackgroundTask mTask;
	private ProgressDialog mProgress;

	public static final String KEY_PAGE_NUMBER = "page";

	int mPage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		Fragment fragment = Fragment.instantiate(getApplicationContext(),
				"com.andymcsherry.proguarddemo.MainFragment");
		Bundle b = new Bundle();
		b.putInt("page", 0);
		fragment.setArguments(b);
		transaction.add(R.id.fragment_container, fragment);
		transaction.commit();

		getSupportFragmentManager().addOnBackStackChangedListener(
				new OnBackStackChangedListener() {

					public void onBackStackChanged() {
						mPage = getSupportFragmentManager()
								.getBackStackEntryCount();
					}
				});
	}

	public void taskButtonClicked(View v) {
		mProgress = new ProgressDialog(this);
		mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgress.setProgress(0);
		mProgress.show();
		if (null != mTask) {
			mTask.cancel(true);
		}
		mTask = new BackgroundTask() {
			@Override
			public void onProgressUpdate(Integer... progress) {
				mProgress.setProgress(progress[0]);
			}

			@Override
			public void onPostExecute(Void result) {
				super.onPostExecute(result);
				if (!isCancelled()) {
					mProgress.hide();

					FragmentTransaction transaction = getSupportFragmentManager()
							.beginTransaction();
					Fragment fragment = Fragment.instantiate(
							getApplicationContext(),
							"com.andymcsherry.proguarddemo.MainFragment");
					Bundle b = new Bundle();
    				b.putInt(KEY_PAGE_NUMBER, ++mPage);
					fragment.setArguments(b);
					transaction.replace(R.id.fragment_container, fragment);
					transaction.addToBackStack("backstack");
					transaction.commit();
				}
			}
		};
		mTask.execute();
	}

	@Override
	public void onDestroy() {
		if (null != mTask) {
			mTask.cancel(true);
			mTask = null;
		}
		mProgress = null;
		super.onDestroy();
	}
}