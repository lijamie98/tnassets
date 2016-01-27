package com.telenav.tnassets.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(ReservedId.class)
@Table(name = "reserved")
public class ReservedEntity {
	@Id
	@Column(name = "resdatetime")
	private Date date;

	@Id
	@Column(name = "resinstanceid")
	private String size;

	@Id
	@Column(name = "resstate")
	private String state;

	@Column(name = "rescount")
	private String count;
	
	@Column(name = "resaz")
	private String az;
	
	@Column(name = "resplatform")
	private String platform;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getAz() {
		return az;
	}

	public void setAz(String az) {
		this.az = az;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public String toString() {
		return "ReservedEntity [date=" + date + ", size=" + size + ", state=" + state + ", count=" + count
				+ ", az=" + az + ", platform=" + platform + "]";
	}
}
