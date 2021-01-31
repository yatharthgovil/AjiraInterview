package com.ajira.node;

public class Node {

	private int index;
	private String name;
	private DeviceType dType;
	private int strength = 5;
	
	public Node() {
		
	}
	public Node(int index,String name, DeviceType dType) {
		this.index = index;
		this.name = name;
		this.dType = dType;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DeviceType getdType() {
		return dType;
	}
	public void setdType(DeviceType dType) {
		this.dType = dType;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
}
