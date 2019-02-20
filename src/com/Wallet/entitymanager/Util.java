package com.Wallet.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Util {
	
	private static EntityManagerFactory emfactory;
	private static EntityManager entitymanager;
	
	public static EntityManager getEntityManager(){
		emfactory = Persistence.createEntityManagerFactory("wallet");
		entitymanager = emfactory.createEntityManager();
		return entitymanager;
	}
	
	public static boolean close(){
		entitymanager.close();
		emfactory.close();
		
		return true;
	}
}
