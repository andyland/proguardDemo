package com.andymcsherry.proguarddemo;

import java.io.Serializable;


public class SensorData implements Serializable {
	
	/*
	 * serialVersionUID is never referenced by any of our code
	 * the runtime environment grabs it automatically, make sure
	 * to keep these! 
	 */
	private static final long serialVersionUID = 1L;
	
	public String name;
	public String vendor;
	public int version;
	public float range;
	public int delay;
	public float power;
	public int type;
}
