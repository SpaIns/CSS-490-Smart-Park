package com.smartpark.firebase;

public class Main {
	
	private final static String datebaseUrl = "https://smartpark-aa8eb.firebaseio.com/";
	private final static String serviceAccountPath = "/home/ubuntu/VMScripts/SmartPark-a58d3074b302.json";
	private final static String spacesFilePath = "/allSpaces.csv";
	
	public static void main(String[] args) {		
		Firebase fb = new Firebase(datebaseUrl, serviceAccountPath, spacesFilePath);
		fb.uploadData(fb.getData());
		System.exit(0);
	}
}
