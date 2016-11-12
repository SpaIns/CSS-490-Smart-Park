package com.smartpark.firebase;

public class Space {
	private int spaceNumber, floorNumber;
	private String garage;
	
	public Space(int spaceNumber, int floorNumber, String garage) {
		this.spaceNumber = spaceNumber;
		this.floorNumber = floorNumber;
		this.garage = garage;
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
}
