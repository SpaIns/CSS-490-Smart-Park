package com.smartpark.firebase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
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
	private int s1, s2, s3, s4, n1, n2, n3, n4;
	final AtomicBoolean done = new AtomicBoolean(false);
	
	public Firebase(String databaseUrl, String serviceAccountPath, String spacesFilePath) {
		this.databaseUrl = databaseUrl;
		this.serviceAccountPath = serviceAccountPath;
		this.spacesFilePath = spacesFilePath;
	}
	
	public Map<String, Object> getData() {
		// Read in space numbers		
		BufferedReader br = null;
		ArrayList<Space> spaces = null;
		String line = "";
		try {
			spaces = new ArrayList<Space>();
			br = new BufferedReader(new FileReader(spacesFilePath));
			while ((line = br.readLine()) != null) {
				String[] space = line.split(",");
				spaces.add(new Space(Integer.parseInt(space[0]), Integer.parseInt(space[1]), space[2]));
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Map<String, Object> spaceUpdates = new HashMap<String, Object>();
		
		for(Space space : spaces) {
			int spaceNumber = space.getSpaceNumber();
			if(space.getGarage().equals("North") && space.getFloorNumber() == 4) {
				spaceUpdates.put(space.getSpaceNumber() + "/isAvailable", "unknown");
			}
			else if((spaceNumber >= 628 && spaceNumber <= 689) || (spaceNumber >= 792 && spaceNumber <= 907)) {
				spaceUpdates.put(space.getSpaceNumber() + "/isAvailable", "unknown");
			}
			else if((spaceNumber >= 126 && spaceNumber <= 133) || (spaceNumber == 2454) || (spaceNumber == 2267)) {
				spaceUpdates.put(space.getSpaceNumber() + "/isAvailable", "unknown");
			}
			else {
				spaceUpdates.put(space.getSpaceNumber() + "/isAvailable", getRandomBool());
			}			
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
	
	private String getRandomBool() {
		Random rand = new Random();
		if(rand.nextInt(100) < 50) {
			return "false";
		}
		else {
			return "true";
		}
	}
}
