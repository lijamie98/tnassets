package com.telenav.tnassets.data.awsprices;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(AwsPriceId.class)
@Table(name = "prices")
public class AwsPriceEntityMySQL implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="datetime")
	private Date datetime;

	@Id
	@Column(name="region")
	private String region;
	
	@Id
	@Column(name="size")
	private String size;
	
	@Id
	@Column(name="type")
	private String type;
	
	@Column(name="price")
	private String price;

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
		return "AwsPriceEntityMySQL [datetime=" + datetime + ", region=" + region + ", size=" + size + ", type=" + type
				+ ", price=" + price + "]";
	}
	
	

}
