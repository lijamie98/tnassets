package com.telenav.tnassets.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the connection database table.
 *
 */
public class AwsPriceEntityES implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;

	private Date datetime;

	private String region;

	private String size;

	private String type;

	private String price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AwsPriceEntityES [id=" + id + ", datetime=" + datetime + ", region=" + region + ", size=" + size
				+ ", type=" + type + ", price=" + price + "]";
	}

}