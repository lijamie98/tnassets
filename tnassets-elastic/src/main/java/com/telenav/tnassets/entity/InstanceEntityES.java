package com.telenav.tnassets.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the connection database table.
 *
 */
public class InstanceEntityES implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private Date date;

	private String instanceid;

	private String account;

	private String name;

	private String ip;

	private String extip;

	private String keyname;

	private String size;

	private String state;

	private String launch;

	private String device;

	private String az;

	private String sg;

	private String srcdst;

	private String mac;

	private String intid;

	private String spot;

	private String vmtype;

	private String owner;

	private String service;

	private String group;

	private String proj;

	private String image;

	private String role;

	private double cpu;

	private double readBytes;

	private double readOps;

	private double writeBytes;

	private double writeOps;

	private long networkIn;

	private long networkOut;

	private long mem;

	private long memFree;

	private long cache;

	private long swap;
	
	private double dedicatedPrice;
	
	private double reservedPrice;
	
	private double averagePrice;
	
	private String pricingType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getExtip() {
		return extip;
	}

	public void setExtip(String extip) {
		this.extip = extip;
	}

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String type) {
		this.size = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLaunch() {
		return launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getAz() {
		return az;
	}

	public void setAz(String az) {
		this.az = az;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public String getSrcdst() {
		return srcdst;
	}

	public void setSrcdst(String srcdst) {
		this.srcdst = srcdst;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIntid() {
		return intid;
	}

	public void setIntid(String intid) {
		this.intid = intid;
	}

	public String getSpot() {
		return spot;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}

	public String getVmtype() {
		return vmtype;
	}

	public void setVmtype(String vmtype) {
		this.vmtype = vmtype;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getProj() {
		return proj;
	}

	public void setProj(String proj) {
		this.proj = proj;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getCpu() {
		return cpu;
	}

	public void setCpu(double cpu) {
		this.cpu = cpu;
	}

	public double getReadBytes() {
		return readBytes;
	}

	public void setReadBytes(double readBytes) {
		this.readBytes = readBytes;
	}

	public double getReadOps() {
		return readOps;
	}

	public void setReadOps(double readOps) {
		this.readOps = readOps;
	}

	public double getWriteBytes() {
		return writeBytes;
	}

	public void setWriteBytes(double writeBytes) {
		this.writeBytes = writeBytes;
	}

	public double getWriteOps() {
		return writeOps;
	}

	public void setWriteOps(double writeOps) {
		this.writeOps = writeOps;
	}

	public long getNetworkIn() {
		return networkIn;
	}

	public void setNetworkIn(long networkIn) {
		this.networkIn = networkIn;
	}

	public long getNetworkOut() {
		return networkOut;
	}

	public void setNetworkOut(long networkOut) {
		this.networkOut = networkOut;
	}

	public long getMem() {
		return mem;
	}

	public void setMem(long mem) {
		this.mem = mem;
	}

	public long getMemFree() {
		return memFree;
	}

	public void setMemFree(long memFree) {
		this.memFree = memFree;
	}

	public long getCache() {
		return cache;
	}

	public void setCache(long cache) {
		this.cache = cache;
	}

	public long getSwap() {
		return swap;
	}

	public void setSwap(long swap) {
		this.swap = swap;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPricingType() {
		return pricingType;
	}

	public void setPricingType(String pricingType) {
		this.pricingType = pricingType;
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

	public double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

	@Override
	public String toString() {
		return "InstanceEntityES [id=" + id + ", date=" + date + ", instanceid=" + instanceid + ", account=" + account
				+ ", name=" + name + ", ip=" + ip + ", extip=" + extip + ", keyname=" + keyname + ", size=" + size
				+ ", state=" + state + ", launch=" + launch + ", device=" + device + ", az=" + az + ", sg=" + sg
				+ ", srcdst=" + srcdst + ", mac=" + mac + ", intid=" + intid + ", spot=" + spot + ", vmtype=" + vmtype
				+ ", owner=" + owner + ", service=" + service + ", group=" + group + ", proj=" + proj + ", image="
				+ image + ", role=" + role + ", cpu=" + cpu + ", readBytes=" + readBytes + ", readOps=" + readOps
				+ ", writeBytes=" + writeBytes + ", writeOps=" + writeOps + ", networkIn=" + networkIn + ", networkOut="
				+ networkOut + ", mem=" + mem + ", memFree=" + memFree + ", cache=" + cache + ", swap=" + swap
				+ ", dedicatedPrice=" + dedicatedPrice + ", reservedPrice=" + reservedPrice + ", averagePrice="
				+ averagePrice + ", pricingType=" + pricingType + "]";
	}
}