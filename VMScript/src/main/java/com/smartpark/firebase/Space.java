package com.smartpark.firebase;

public class Space {
	private int spaceNumber, floorNumber;
	private String garage, isAvailable, spaceType;
	
	public Space(int spaceNumber, int floorNumber, String garage, String isAvailable, String spaceType) {
		this.spaceNumber = spaceNumber;
		this.floorNumber = floorNumber;
		this.garage = garage;
		this.isAvailable = isAvailable;
        this.spaceType = spaceType;
	}
	
	public int getSpaceNumber() {
		return spaceNumber;
	}

	public void setSpaceNumber(int spaceNumber) {
		this.spaceNumber = spaceNumber;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getGarage() {
		return garage;
	}

	public void setGarage(String garage) {
		this.garage = garage;
	}
	
	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
    
    public String getSpaceType(){
        return this.spaceType;
    }
}
