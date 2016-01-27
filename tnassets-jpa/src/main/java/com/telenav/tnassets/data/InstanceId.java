package com.telenav.tnassets.data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class InstanceId implements Serializable {
	Date date;
	String instanceid;

	public int hashCode() {
		return (int) instanceid.hashCode() + date.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof InstanceId))
			return false;
		InstanceId pk = (InstanceId) obj;
		return pk.date == date && pk.instanceid.equals(instanceid);
	}
}
