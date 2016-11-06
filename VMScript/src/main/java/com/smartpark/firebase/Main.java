package com.smartpark.firebase;

public class Main {
	public static void main(String[] args) {
		Firebase fb = new Firebase("https://smartpark-aa8eb.firebaseio.com/", "C:/Users/Tyler/Desktop/SmartPark-a58d3074b302.json");
		fb.uploadData(fb.getData());
		System.exit(0);
	}
}
