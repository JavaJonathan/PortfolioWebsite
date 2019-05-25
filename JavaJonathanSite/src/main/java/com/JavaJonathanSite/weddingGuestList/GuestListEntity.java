package com.JavaJonathanSite.weddingGuestList;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GuestListEntity
{
	@Id
	public String id;
	
	//forced to be of collection type because of element collection
	@ElementCollection
	public Collection<Guest> guestList = new ArrayList<>();	
	
	//default constructor to prevent errors
	public GuestListEntity() {}
	
	public GuestListEntity(String id, ArrayList<Guest> arrayList) 
	{
		this.id = id;
		this.guestList = arrayList;
	}
	
	public void setId(String id) { this.id = id; }
	public String getId() { return id; }
	
	public void setList(ArrayList<Guest> guestList)	{ this.guestList = guestList; }
	public Collection<Guest> getGuestList() { return guestList; }
	
	
}
