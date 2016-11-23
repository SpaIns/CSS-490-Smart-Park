package com.smartpark.firebase;

import java.util.ArrayList;

public class Main {
	
	private final static String datebaseUrl = "https://smartpark-aa8eb.firebaseio.com/";
	private final static String serviceAccountPath = "C:/Users/Tyler/Dropbox/CSS 490 Developing Modern Software/SmartPark-a58d3074b302.json";
	private final static String spacesFilePath = "C:/Users/Tyler/gitsource/CSS-490-Smart-Park/VMScript/src/main/resources/allSpaces.csv";
	
	public static void main(String[] args) {		
		Firebase fb = new Firebase(datebaseUrl, serviceAccountPath, spacesFilePath);
		ArrayList<Space> spaces = fb.parseAllSpaces();
		fb.uploadData(fb.getData(spaces), "spaces");
		fb.uploadData(fb.getTotalCounts(), "totals");
		System.exit(0);
	}
}
