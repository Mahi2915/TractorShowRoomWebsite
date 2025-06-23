package com.web.ashoktractors;

public class ServiceDTO {
	
	
	
private int typeId;
private String typeName;


public ServiceDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public ServiceDTO(int typeId, String typeName) {
	super();
	this.typeId = typeId;
	this.typeName = typeName;
}
public int getTypeId() {
	return typeId;
}
public void setTypeId(int typeId) {
	this.typeId = typeId;
}
public String getTypeName() {
	return typeName;
}
public void setTypeName(String typeName) {
	this.typeName = typeName;
}



}
