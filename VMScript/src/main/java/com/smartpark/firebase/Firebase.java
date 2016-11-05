package com.smartpark.firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firebase {
	
	private static final String databaseUrl = "https://smartpark-aa8eb.firebaseio.com/";
	private static final String serviceAccountPath = "C:/Users/Tyler/Desktop/SmartPark-a58d3074b302.json";
	final static AtomicBoolean done = new AtomicBoolean(false);
	
	public static void main(String[] args) {
		uploadData(getData());
		System.exit(0);
	}
	
	public static Map<String, Object> getData() {
		Map<String, Object> spaceUpdates = new HashMap<String, Object>();
		
		spaceUpdates.put("126/isAvailable", true);
		spaceUpdates.put("127/isAvailable", false);
		
		return spaceUpdates;
	}
	
	public static void uploadData(Map<String, Object> updates) {
		FirebaseOptions options;
		try {
			options = new FirebaseOptions.Builder()
			  .setServiceAccount(new FileInputStream(serviceAccountPath))
			  .setDatabaseUrl(databaseUrl)
			  .build();
			FirebaseApp.initializeApp(options);
			
			DatabaseReference ref = FirebaseDatabase
					.getInstance()
				    .getReference("spaces");
						
			ref.updateChildren(updates, new CompletionListener() {
				@Override
				public void onComplete(DatabaseError databaseError, DatabaseReference database) {
					done.set(true);
					
					if (databaseError != null) {
			            System.out.println("Data could not be updated. " + databaseError.getMessage());
			        } else {
			            System.out.println("Data updated successfully.");
			        }
				}
			});
			while (!done.get());
			return;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
