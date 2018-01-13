package com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

 
public class Model 
{
	String sname,spass;
	String sid;
	//String Person  = null;
	
	
	/*public String getPerson() {
		return Person;
	}
	public void setPerson(String person) {
		Person = person;
	}*/
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSpass() {
		return spass;
	}
	public void setSpass(String spass) {
		this.spass = spass;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	@Override
	public String toString() {
		return "Model [sname=" + sname + ", spass=" + spass + ", sid=" + sid + "]";
	}
	
	

}
