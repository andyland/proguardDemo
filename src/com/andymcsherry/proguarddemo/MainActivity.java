package com.andymcsherry.proguarddemo;

import android.os.Bundle;
/*
 * 3rd Party Libraries should have blanket coverage
 * It's very hard to know the side effects.
 * The Android support libraries use A LOT of reflection
 * so it's best to be safe and just cover it all
 * 
 * -keep class android.** {*;}
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

/* 
 *   Activities are kept in the default
 */
public class MainActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Log.e("Demo","This shouldn't show up when I obfuscate because I've removed logging!");

		if (null == savedInstanceState) {
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			/*
			 * Make sure to save fragments, they're often loaded dynamically
			 */
			Fragment fragment = Fragment.instantiate(getApplicationContext(), "com.andymcsherry.proguarddemo.SensorListFragment");;
			transaction.add(R.id.fragment_container, fragment);
			transaction.commit();
		}
	}
	
	/*
	 * Keep the names of native methods!
	 * If it changes, the NDK won't be able
	 * to find your C method
	 */
	public native void doSomething();

	/*
	 * proguard will remove android:onclick methods
	 * because they're never called in your java!
	 */
	public void goBackButtonClicked(View v) {
		getSupportFragmentManager().popBackStack();
	}
}