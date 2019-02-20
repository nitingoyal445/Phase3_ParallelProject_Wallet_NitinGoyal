package com.Wallet.service;

import java.math.BigDecimal;
//import java.util.LinkedList;
import java.util.List;

import com.Wallet.bean.Customer;
//import com.Wallet.bean.Transaction;
import com.Wallet.exception.DuplicateMobileNumberException;
import com.Wallet.exception.InsufficientAmountException;
import com.Wallet.exception.MobileNoDoesNotExistException;

public interface WalletService {

	public Customer createAccount(String name,String mobileNo,BigDecimal amount) throws DuplicateMobileNumberException;
	public Customer showBalance(String mobileNo) throws MobileNoDoesNotExistException;
	public List<Customer> fundTransfer(String sourceMobileNo,String targetMobileNo,BigDecimal amount) throws MobileNoDoesNotExistException,InsufficientAmountException;
	public Customer depositAmount(String mobileNo,BigDecimal amount)throws MobileNoDoesNotExistException;
	public Customer withdrawAmount(String mobileNo,BigDecimal amount)throws MobileNoDoesNotExistException,InsufficientAmountException;
	
	
	
}
