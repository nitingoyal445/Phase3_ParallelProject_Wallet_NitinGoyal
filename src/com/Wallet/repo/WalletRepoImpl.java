package com.Wallet.repo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.Wallet.bean.Customer;
import com.Wallet.entitymanager.Util;

public class WalletRepoImpl implements WalletRepo{

	Customer customer;
	EntityTransaction entityTransaction;
	int flag=0;
	private EntityManager entitymanager;
	public WalletRepoImpl() {
		
		entitymanager = Util.getEntityManager();
	}

	@Override
	public boolean save(Customer customer) {
		
		entitymanager.getTransaction().begin();
		entitymanager.persist(customer);
		entitymanager.getTransaction().commit();
		return true;
	
	}
	@Override
	public Customer findOne(String mobileNo) {
		Customer customer=entitymanager.find(Customer.class, mobileNo);
		return customer;
	}
	@Override
	public boolean update(Customer customer) {
		entitymanager.getTransaction().begin();
		Customer cus=entitymanager.find(Customer.class, customer.getMobileNo());
		cus.setWallet(customer.getWallet());
		entitymanager.getTransaction().commit();
		return true;
	}

	public boolean closeConnection() {
		Util.close();
		return true;
	}

	
}
			

