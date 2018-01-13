package com;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;
import java.util.UUID;

public class CustomIdGenerator implements IdentifierGenerator {

	public Serializable generate(SharedSessionContractImplementor arg0, Object arg1) throws HibernateException {
		
		String uID =getRanndomNumber();
		return "AB_"+uID;
	}
	
	public String getRanndomNumber()
	{
		 final UUID uid = UUID.randomUUID();
		 long mbit=uid.getMostSignificantBits();
		// long lbit  =  uid.getLeastSignificantBits();
		 return mbit+"";//+lbit;
	}
	
	

}
