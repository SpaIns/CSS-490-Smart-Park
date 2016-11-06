package com.smartpark.firebase;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
	
	private String databaseUrl, serviceAccountPath;
	final AtomicBoolean done = new AtomicBoolean(false);
	
	public Firebase(String databaseUrl, String serviceAccountPath) {
		this.databaseUrl = databaseUrl;
		this.serviceAccountPath = serviceAccountPath;
		
	}
	
	public Map<String, Object> getData() {
		Map<String, Object> spaceUpdates = new HashMap<String, Object>();
		
		// Put the values we want to update here
		spaceUpdates.put("126/isAvailable", true);
		spaceUpdates.put("127/isAvailable", false);
		
		return spaceUpdates;
	}
	
	public void uploadData(Map<String, Object> updates) {
		FirebaseOptions options;
		try {
			// Configure Firebase options
			options = new FirebaseOptions.Builder()
			  .setServiceAccount(new FileInputStream(serviceAccountPath))
			  .setDatabaseUrl(databaseUrl)
			  .build();
			FirebaseApp.initializeApp(options);
			
			// Get the spaces key reference
			DatabaseReference ref = FirebaseDatabase
					.getInstance()
				    .getReference("spaces");
						
			// Update the db with the new data
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
			while (!done.get());		// wait until updateChildren is done updating the db
			return;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
