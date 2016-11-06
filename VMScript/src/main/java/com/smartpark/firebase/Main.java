package com.smartpark.firebase;

public class Main {
	
	private final static String datebaseUrl = "https://smartpark-aa8eb.firebaseio.com/";
	private final static String serviceAccountPath = "C:/Users/Tyler/Desktop/SmartPark-a58d3074b302.json";
	
	public static void main(String[] args) {
		Firebase fb = new Firebase(datebaseUrl, serviceAccountPath);
		fb.uploadData(fb.getData());
		System.exit(0);
	}
}
