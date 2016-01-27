package com.telenav.tnassets.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class StatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "datetime")
	private Date datetime;

	@Column(name = "component")
	private String component;

	@Column(name = "status")
	private String status;

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusEntity [datetime=" + datetime + ", component=" + component + ", status=" + status + "]";
	}
}
