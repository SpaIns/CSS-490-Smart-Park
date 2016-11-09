package com.smartpark.firebase;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
	
	private String databaseUrl, serviceAccountPath, spacesFilePath;
	final AtomicBoolean done = new AtomicBoolean(false);
	
	public Firebase(String databaseUrl, String serviceAccountPath, String spacesFilePath) {
		this.databaseUrl = databaseUrl;
		this.serviceAccountPath = serviceAccountPath;
		this.spacesFilePath = spacesFilePath;
	}
	
	public Map<String, Object> getData() {
		// Read in space numbers
		ClassLoader classLoader = getClass().getClassLoader();
		File spacesFile = new File(classLoader.getResource(spacesFilePath).getFile());
		
		Scanner s = null;
		ArrayList<String> spaces = null;
		try {
			s = new Scanner(spacesFile);
			spaces = new ArrayList<String>();
			while(s.hasNext()) {
				spaces.add(s.next());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		
		
		Map<String, Object> spaceUpdates = new HashMap<String, Object>();
		
		for(String space : spaces) {
			spaceUpdates.put(space + "/isAvailable", getRandomBool());
		}
		
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
	
	private boolean getRandomBool() {
		Random rand = new Random();
		if(rand.nextInt(100) < 50) {
			return false;
		}
		else {
			return true;
		}
	}
}
