package com.smartpark.firebase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
	
	private String databaseUrl, serviceAccountPath, spacesFilePath;
	private int s1, s2, s3, s4, n1, n2, n3;		// n4, s5, and half of s4 don't have sensors
	final AtomicBoolean done = new AtomicBoolean(false);
	
	public Firebase(String databaseUrl, String serviceAccountPath, String spacesFilePath) {
		this.databaseUrl = databaseUrl;
		this.serviceAccountPath = serviceAccountPath;
		this.spacesFilePath = spacesFilePath;
		s1 = s2 = s3 = s4 = n1 = n2 = n3 = 0;
	}
	
	public Map<String, Object> getData() {
		ArrayList<Space> spaces = parseAllSpaces();
		Map<String, Object> spaceUpdates = new HashMap<String, Object>();
		
		for(Space space : spaces) {
			spaceUpdates.put(space.getSpaceNumber() + "/isAvailable", space.getIsAvailable());	
		}
		
		return spaceUpdates;
	}

	private ArrayList<Space> parseAllSpaces() {
		// Read in space numbers		
		BufferedReader br = null;
		ArrayList<Space> spaces = null;
		String line = "";
		try {
			spaces = new ArrayList<Space>();
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(spacesFilePath)));
			while ((line = br.readLine()) != null) {
				String[] space = line.split(",");
				int spaceNumber = Integer.parseInt(space[0]);
				int floorNumber = Integer.parseInt(space[1]);
				String garage = space[2];
                String spaceType = space[3];
				String isAvailable = getRandomBool();                
				
				// All spaces on the 4th floor North Garage don't have sensors
				if(garage.equals("North") && floorNumber == 4) {
					isAvailable = "unknown";
				}
				// These spaces are normal spaces that don't have sensors
				else if((spaceNumber >= 628 && spaceNumber <= 689) || (spaceNumber >= 792 && spaceNumber <= 907)) {
					isAvailable = "unknown";
				}
				// These spaces are motorcycle spaces which don't have sensors
				else if((spaceNumber >= 126 && spaceNumber <= 133) || (spaceNumber == 2454) || (spaceNumber == 2267)) {
					isAvailable = "unknown";
				}
				
				spaces.add(new Space(spaceNumber, floorNumber, garage, isAvailable, spaceType));
				
				// Calculate total available spaces per garage per floor
				if(garage.equals("North") && !isAvailable.equals("unknown")){
					if(floorNumber == 1) {
						n1++;
					}
					else if(floorNumber == 2) {
						n2++;
					}
					else if(floorNumber == 3) {
						n3++;
					}
				}
				else if(garage.equals("South") && !isAvailable.equals("unknown")) {
					if(floorNumber == 1) {
						s1++;
					}
					else if(floorNumber == 2) {
						s2++;
					}
					else if(floorNumber == 3) {
						s3++;
					}
					else if(floorNumber == 4) {
						s4++;
					}
				}
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return spaces;
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
