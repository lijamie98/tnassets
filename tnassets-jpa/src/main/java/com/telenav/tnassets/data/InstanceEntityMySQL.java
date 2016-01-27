package com.telenav.tnassets.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * The persistent class for the connection database table.
 *
 */
@Entity
@IdClass(InstanceId.class)
@Table(name = "instances")
public class InstanceEntityMySQL implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "instdate")
	private Date date;

	@Id
	@Column(name = "instanceid")
	private String instanceid;

	@Column(name = "instname")
	private String name;

	@Column(name = "instip")
	private String ip;

	@Column(name = "instextip")
	private String extip;

	@Column(name = "instkeyname")
	private String keyname;

	@Column(name = "insttype")
	private String size;

	@Column(name = "inststate")
	private String state;

	@Column(name = "instlaunch")
	private String launch;

	@Column(name = "instdevice")
	private String device;

	@Column(name = "instaz")
	private String az;

	@Column(name = "instsg")
	private String sg;

	@Column(name = "instsrcdst")
	private String srcdst;

	@Column(name = "instmac")
	private String mac;

	@Column(name = "instintid")
	private String intid;

	@Column(name = "instspot")
	private String spot;

	@Column(name = "instvmtype")
	private String vmtype;

	@Column(name = "instowner")
	private String owner;

	@Column(name = "instservice")
	private String service;

	@Column(name = "instgroup")
	private String group;

	@Column(name = "instproj")
	private String proj;

	@Column(name = "instimage")
	private String image;

	@Column(name = "instrole")
	private String role;

	@Column(name = "instcpu")
	private double cpu;

	@Column(name = "instreadbytes")
	private double readBytes;

	@Column(name = "instreadops")
	private double readOps;

	@Column(name = "instwritebytes")
	private double writeBytes;

	@Column(name = "instwriteops")
	private double writeOps;

	@Column(name = "instnetworkin")
	private long networkIn;

	@Column(name = "instnetworkout")
	private long networkOut;

	@Column(name = "instmem")
	private long mem;

	@Column(name = "instmemfree")
	private long memFree;

	@Column(name = "instcache")
	private long cache;

	@Column(name = "instswap")
	private long swap;

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

	@Override
	public String toString() {
		return "InstanceEntityMySQL [date=" + date + ", instanceid=" + instanceid + ", name=" + name + ", ip=" + ip
				+ ", extip=" + extip + ", keyname=" + keyname + ", type=" + size + ", state=" + state + ", launch="
				+ launch + ", device=" + device + ", az=" + az + ", sg=" + sg + ", srcdst=" + srcdst + ", mac=" + mac
				+ ", intid=" + intid + ", spot=" + spot + ", vmtype=" + vmtype + ", owner=" + owner + ", service="
				+ service + ", group=" + group + ", proj=" + proj + ", image=" + image + ", role=" + role + ", cpu="
				+ cpu + ", readBytes=" + readBytes + ", readOps=" + readOps + ", writeBytes=" + writeBytes
				+ ", writeOps=" + writeOps + ", networkIn=" + networkIn + ", networkOut=" + networkOut + ", mem=" + mem
				+ ", memFree=" + memFree + ", cache=" + cache + ", swap=" + swap + "]";
	}

}