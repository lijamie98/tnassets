package com.telenav.tnassets.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(InstanceId.class)
@Table(name = "spot")
public class SpotInstanceEntity {

	@Id
	@Column(name = "spotdate")
	private Date date;

	@Id
	@Column(name = "spotid")
	private String instanceid;

	@Column(name = "spotprice")
	private Float price;

	@Column(name = "spottype")
	private String type;

	@Column(name = "spotstate")
	private String state;
	
	@Column(name = "spotcode")
	private String code;
	
	@Column(name = "spotmessage")
	private String message;
	
	@Column(name = "spotinstance")
	private String instance;
	
	@Column(name = "spotcreate")
	private String create;

	@Column(name = "spotaz")
	private String az;

	@Column(name = "spotsize")
	private String size;

	@Column(name = "spotinstname")
	private String instname;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInstanceid() {
		return instanceid;
	}

	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getCreate() {
		return create;
	}

	public void setCreate(String create) {
		this.create = create;
	}

	public String getAz() {
		return az;
	}

	public void setAz(String az) {
		this.az = az;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getInstname() {
		return instname;
	}

	public void setInstname(String instname) {
		this.instname = instname;
	}

	@Override
	public String toString() {
		return "SpotInstanceEntity [date=" + date + ", instanceid=" + instanceid + ", price=" + price + ", type=" + type
				+ ", state=" + state + ", code=" + code + ", message=" + message + ", instance=" + instance
				+ ", create=" + create + ", az=" + az + ", size=" + size + ", instname=" + instname + "]";
	}
	
	
}
