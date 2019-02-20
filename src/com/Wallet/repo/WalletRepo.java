package com.Wallet.repo;
import com.Wallet.bean.Customer;
public interface WalletRepo {
	public boolean save(Customer customer);
	public Customer findOne(String mobileNo);
	public boolean update(Customer customer);
	
	
	
}
