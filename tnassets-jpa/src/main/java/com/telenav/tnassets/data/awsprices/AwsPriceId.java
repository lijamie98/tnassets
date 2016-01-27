package com.telenav.tnassets.data.awsprices;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class AwsPriceId implements Serializable {
	Date datetime;
	String size;
	String region;
	String type;

	public int hashCode() {
		return (int) type.hashCode() + size.hashCode() + datetime.hashCode() + region.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof AwsPriceId))
			return false;
		AwsPriceId pk = (AwsPriceId) obj;
		return pk.datetime == datetime && pk.size.equals(size) && pk.region.equals(region) && pk.type.equals(type);
	}
}
