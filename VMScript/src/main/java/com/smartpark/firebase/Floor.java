package com.smartpark.firebase;

public class Floor {
	private int regCount, uCarCount, disCount, carpoolCount, reservedCount, evCount, total;
	
	public int getRegCount() {
		return regCount;
	}

	public void setRegCount(int regCount) {
		this.regCount = regCount;
	}

	public int getuCarCount() {
		return uCarCount;
	}

	public void setuCarCount(int uCarCount) {
		this.uCarCount = uCarCount;
	}

	public int getDisCount() {
		return disCount;
	}

	public void setDisCount(int disCount) {
		this.disCount = disCount;
	}

	public int getCarpoolCount() {
		return carpoolCount;
	}

	public void setCarpoolCount(int carpoolCount) {
		this.carpoolCount = carpoolCount;
	}

	public int getReservedCount() {
		return reservedCount;
	}

	public void setReservedCount(int reservedCount) {
		this.reservedCount = reservedCount;
	}

	public int getEvCount() {
		return evCount;
	}

	public void setEvCount(int evCount) {
		this.evCount = evCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Floor() {
		regCount = uCarCount = disCount = carpoolCount = reservedCount = evCount = 0;
	}
	
	public void incrementCount(Space space) {
		switch (space.getSpaceType()){
			case "REG":
				regCount++;
				break;
			case "U-Car":
				uCarCount++;
				break;
			case "DIS":
				disCount++;
				break;
			case "Carpool":
				carpoolCount++;
				break;
			case "Reserved":
				reservedCount++;
				break;
			case "EV":
				evCount++;
				break;
			default:
				throw new IllegalArgumentException("Invalid space type");
		}
		total++;
		return;
	}
}
