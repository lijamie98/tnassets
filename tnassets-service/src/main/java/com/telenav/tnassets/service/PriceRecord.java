package com.telenav.tnassets.service;

public class PriceRecord {
	private int reservedCount;
	private int instanceCount;
	private String size;
	private String az;
	private double dedicatedPrice;
	private double reservedPrice;

	public PriceRecord(String size, String az, int reservedCount) {
		this.size = size;
		this.az = az;
		this.reservedCount = reservedCount;
		this.instanceCount = 0;
	}

	public int getReservedCount() {
		return reservedCount;
	}

	public void setReservedCount(int reservedCount) {
		this.reservedCount = reservedCount;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getAz() {
		return az;
	}

	public void setAz(String az) {
		this.az = az;
	}

	public double getDedicatedPrice() {
		return dedicatedPrice;
	}

	public void setDedicatedPrice(double dedicatedPrice) {
		this.dedicatedPrice = dedicatedPrice;
	}

	public double getReservedPrice() {
		return reservedPrice;
	}

	public void setReservedPrice(double reservedPrice) {
		this.reservedPrice = reservedPrice;
	}

	/**
	 * max(Cr * Pr, Cr * Pr + max(0, C-Cr) * P(d))
	 * Cr = reserved count
	 * Pr = reserved price
	 * C = instance count
	 * Pd = dedicated price
	 * 
	 * @return
	 */
	public double getAveragePrice() {
		if (instanceCount <= 0) {
			return 0;
		}
		double totalCost = Math.max(reservedPrice * reservedCount, reservedPrice * reservedCount + Math.max(0, instanceCount - reservedCount) * dedicatedPrice);

		return totalCost / (double) instanceCount;
	}

	public void incrementInstance() {
		instanceCount++;
	}
	
	public String getKey() {
		return getKey(size, az);
	}
	
	public static String getKey(String size, String az) {
		return size + az;
	}

	@Override
	public String toString() {
		return "PriceRecord [reservedCount=" + reservedCount + ", instanceCount=" + instanceCount + ", size=" + size
				+ ", az=" + az + ", dedicatedPrice=" + dedicatedPrice + ", reservedPrice=" + reservedPrice
				+ ", getAveragePrice()=" + getAveragePrice() + "]";
	}

	
	
	
}
