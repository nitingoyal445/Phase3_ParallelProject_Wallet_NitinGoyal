package com.Wallet.service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import com.Wallet.bean.Customer;
import com.Wallet.bean.Wallet;
import com.Wallet.exception.DuplicateMobileNumberException;
import com.Wallet.exception.InsufficientAmountException;
import com.Wallet.exception.MobileNoDoesNotExistException;
import com.Wallet.repo.WalletRepo;

public class WalletServiceImpl implements WalletService {
	
	WalletRepo walletRepo;
	
	public WalletServiceImpl(WalletRepo walletRepo) {
		super();
		this.walletRepo = walletRepo;
	}
	
	Customer customer;
	Wallet w;
	int counter=1;
	
	@Override
	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws DuplicateMobileNumberException {
		customer=new Customer();
		customer.setName(name);
		customer.setMobileNo(mobileNo);
		if(walletRepo.findOne(mobileNo)!=null) {
			throw new DuplicateMobileNumberException();
		}
		w=new Wallet();
		w.setBalance(amount);
		customer.setWallet(w);
		walletRepo.save(customer);
		return customer;
	}

	@Override
	public Customer showBalance(String mobileNo) throws MobileNoDoesNotExistException {
		if(walletRepo.findOne(mobileNo)==null)
			throw new MobileNoDoesNotExistException();
		return walletRepo.findOne(mobileNo);
	}

	@Override
	public List<Customer> fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws MobileNoDoesNotExistException, InsufficientAmountException {
		Customer customer1=new Customer();
		List<Customer> list=new LinkedList<>();
		customer1=walletRepo.findOne(sourceMobileNo);
		if(customer1!=null) {
			Customer customer2=new Customer();
			customer2=walletRepo.findOne(targetMobileNo);
			if(customer2!=null) {
				list.add(withdrawAmount(sourceMobileNo, amount));
				list.add(depositAmount(targetMobileNo, amount));
			}	
		}
		return list;
	}
	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws MobileNoDoesNotExistException {
		customer=showBalance(mobileNo);
		w=customer.getWallet();
		w.setBalance(w.getBalance().add(amount));
		customer.setWallet(w);
		walletRepo.update(customer);
		
		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws MobileNoDoesNotExistException, InsufficientAmountException {
		
		customer=showBalance(mobileNo);
		w=customer.getWallet();
		if(w.getBalance().compareTo(amount)==-1)
			throw new InsufficientAmountException();
		w.setBalance(w.getBalance().subtract(amount));
		customer.setWallet(w);
		walletRepo.update(customer);
		
		return customer;
	}

	
}
