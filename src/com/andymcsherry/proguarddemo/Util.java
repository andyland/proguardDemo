package com.andymcsherry.proguarddemo;

import android.app.Activity;
import android.util.DisplayMetrics;

public class Util {

	public static int getYPixels(Activity context, float dps) {
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return (int)(dps * metrics.ydpi / 160);
	}
	
	public static int getXPixels(Activity context, float dps) {
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return (int)(dps * metrics.xdpi / 160);
	}
	
	/**
	 * Not used, should be optimzed away by proguard
	 * @param context
	 * @param dps
	 * @return
	 */
	public static int getScaledPixels(Activity context, float dps) {
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return (int)(dps * metrics.scaledDensity / 160);
	}
	
	/**
	 * Not used, should be optimzed away by proguard
	 * @param context
	 * @param dps
	 * @return
	 */
	public static int getPixels(Activity context, float dps) {
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return (int)(dps * metrics.density / 160);
	}
}
