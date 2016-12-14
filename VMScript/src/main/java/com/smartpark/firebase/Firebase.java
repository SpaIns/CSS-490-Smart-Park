package com.smartpark.firebase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	private Floor north1, north2, north3, south1, south2, south3, south4;
	AtomicBoolean done = new AtomicBoolean(false);
	
	public Firebase(String databaseUrl, String serviceAccountPath, String spacesFilePath) {
		this.databaseUrl = databaseUrl;
		this.serviceAccountPath = serviceAccountPath;
		this.spacesFilePath = spacesFilePath;
		
		north1 = new Floor();
		north2 = new Floor();
		north3 = new Floor();
		south1 = new Floor();
		south2 = new Floor();
		south3 = new Floor();
		south4 = new Floor();
	}
	
	public Map<String, Object> getData(ArrayList<Space> spaces) {
		Map<String, Object> spaceUpdates = new HashMap<String, Object>();
		
		for(Space space : spaces) {
			spaceUpdates.put(space.getSpaceNumber() + "/isAvailable", space.getIsAvailable());	
		}
		
		return spaceUpdates;
	}
	
	public Map<String, Object> getTotalCounts(ArrayList<Space> spaces) {
		Map<String, Object> totalUpdates = new HashMap<String, Object>();
		
		for(Space space : spaces) {
			switch(space.getGarage()) {
				case "North":
					switch(space.getFloorNumber()) {
						case 1:
							north1.incrementCount(space);
							break;
						case 2:
							north2.incrementCount(space);
							break;
						case 3:
							north3.incrementCount(space);
							break;
						case 4:
							break;
						default:
							throw new IllegalArgumentException("Invalid floor");
					}
					break;
				case "South":
					switch(space.getFloorNumber()) {
						case 1:
							south1.incrementCount(space);
							break;
						case 2:
							south2.incrementCount(space);
							break;
						case 3:
							south3.incrementCount(space);
							break;
						case 4:
							south4.incrementCount(space);
							break;
						case 5:
							break;
						default:
							throw new IllegalArgumentException("Invalid floor");
					}
					break;
				default:
					throw new IllegalArgumentException("Invalid garage");
			}
		}
		
		// North 1
		totalUpdates.put("north1REG", north1.getRegCount());
		totalUpdates.put("north1UCar", north1.getuCarCount());
		totalUpdates.put("north1DIS", north1.getDisCount());
		totalUpdates.put("north1Carpool", north1.getCarpoolCount());
		totalUpdates.put("north1Reserved", north1.getReservedCount());
		totalUpdates.put("north1EV", north1.getEvCount());
		totalUpdates.put("north1Total", north1.getTotal());

		// North 2
		totalUpdates.put("north2REG", north2.getRegCount());
		totalUpdates.put("north2UCar", north2.getuCarCount());
		totalUpdates.put("north2DIS", north2.getDisCount());
		totalUpdates.put("north2Carpool", north2.getCarpoolCount());
		totalUpdates.put("north2Reserved", north2.getReservedCount());
		totalUpdates.put("north2EV", north2.getEvCount());
		totalUpdates.put("north2Total", north2.getTotal());
		
		// North 3
		totalUpdates.put("north3REG", north3.getRegCount());
		totalUpdates.put("north3UCar", north3.getuCarCount());
		totalUpdates.put("north3DIS", north3.getDisCount());
		totalUpdates.put("north3Carpool", north3.getCarpoolCount());
		totalUpdates.put("north3Reserved", north3.getReservedCount());
		totalUpdates.put("north3EV", north3.getEvCount());
		totalUpdates.put("north3Total", north3.getTotal());
		
		// South 1
		totalUpdates.put("south1REG", south1.getRegCount());
		totalUpdates.put("south1UCar", south1.getuCarCount());
		totalUpdates.put("south1DIS", south1.getDisCount());
		totalUpdates.put("south1Carpool", south1.getCarpoolCount());
		totalUpdates.put("south1Reserved", south1.getReservedCount());
		totalUpdates.put("south1EV", south1.getEvCount());
		totalUpdates.put("south1Total", south1.getTotal());
		
		// South 2
		totalUpdates.put("south2REG", south2.getRegCount());
		totalUpdates.put("south2UCar", south2.getuCarCount());
		totalUpdates.put("south2DIS", south2.getDisCount());
		totalUpdates.put("south2Carpool", south2.getCarpoolCount());
		totalUpdates.put("south2Reserved", south2.getReservedCount());
		totalUpdates.put("south2EV", south2.getEvCount());
		totalUpdates.put("south2Total", south2.getTotal());
		
		// South 3
		totalUpdates.put("south3REG", south3.getRegCount());
		totalUpdates.put("south3UCar", south3.getuCarCount());
		totalUpdates.put("south3DIS", south3.getDisCount());
		totalUpdates.put("south3Carpool", south3.getCarpoolCount());
		totalUpdates.put("south3Reserved", south3.getReservedCount());
		totalUpdates.put("south3EV", south3.getEvCount());
		totalUpdates.put("south3Total", south3.getTotal());
		
		// South 4
		totalUpdates.put("south4REG", south4.getRegCount());
		totalUpdates.put("south4UCar", south4.getuCarCount());
		totalUpdates.put("south4DIS", south4.getDisCount());
		totalUpdates.put("south4Carpool", south4.getCarpoolCount());
		totalUpdates.put("south4Reserved", south4.getReservedCount());
		totalUpdates.put("south4EV", south4.getEvCount());
		totalUpdates.put("south4Total", south4.getTotal());
		
		return totalUpdates;
	}

	public ArrayList<Space> parseAllSpaces() {
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
				else if(spaceType.equals("Motorcycle")) {
					isAvailable = "unknown";
				}
				Space spaceToAdd = new Space(spaceNumber, floorNumber, garage, isAvailable, spaceType);
				
				spaces.add(spaceToAdd);
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return spaces;
	}
	
	public boolean initializeFirebase() {
		// Configure Firebase options
		FirebaseOptions options;
		try {
			options = new FirebaseOptions.Builder()
			  .setServiceAccount(new FileInputStream(serviceAccountPath))
			  .setDatabaseUrl(databaseUrl)
			  .build();
			FirebaseApp.initializeApp(options);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void uploadData(Map<String, Object> updates, String reference) {
		done = new AtomicBoolean(false);
		
		// Get the spaces key reference
		DatabaseReference ref = FirebaseDatabase
				.getInstance()
			    .getReference(reference);
					
		// Update the db with the new data
		ref.updateChildren(updates, new CompletionListener() {
			@Override
			public void onComplete(DatabaseError databaseError, DatabaseReference database) {
				done.set(true);
				
				if (databaseError != null) {
		            System.out.println("Data could not be updated. " + databaseError.getMessage());
		        } else {
		            System.out.println("Data updated successfully to " + reference + ".");
		        }
			}
		});
		while (!done.get());		// wait until updateChildren is done updating the db
		return;
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
