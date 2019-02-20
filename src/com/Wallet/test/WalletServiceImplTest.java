package com.Wallet.test;

//import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.Wallet.exception.DuplicateMobileNumberException;
import com.Wallet.exception.InsufficientAmountException;
import com.Wallet.exception.MobileNoDoesNotExistException;
import com.Wallet.repo.WalletRepoImpl;
import com.Wallet.service.WalletServiceImpl;

public class WalletServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void WhenEnteredDetailOfClientIsCorrectAndSuccessfull() throws DuplicateMobileNumberException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		wsi.createAccount("nitin", "1234512145", new BigDecimal(500));
	}
	
	@Test(expected=com.Wallet.exception.DuplicateMobileNumberException.class)
	public void WhenEnteredMobileNoIsAlreadyExist() throws DuplicateMobileNumberException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		wsi.createAccount("nitin", "1234512345", new BigDecimal(500));
		wsi.createAccount("manish", "5432154321", new BigDecimal(1000));
	}
	@Test
	public void WhenEnteredMobileNumberIsCorrectWhileShowBalance() throws MobileNoDoesNotExistException, DuplicateMobileNumberException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		wsi.createAccount("nitin", "7351117071", new BigDecimal(500));
		wsi.showBalance("7351117071");
	}
	
	@Test(expected=com.Wallet.exception.MobileNoDoesNotExistException.class)
	public void WhenMobileNumberDoesNotExistWhileShowBalance() throws MobileNoDoesNotExistException, DuplicateMobileNumberException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		//wsi.createAccount("nitin", "7351117072", new BigDecimal(500));
		wsi.showBalance("1456327894");
	}
	
	@Test
	public void WhenDetailsEnteredForFundTransferIsCorrectAndSuccessfull() throws DuplicateMobileNumberException, MobileNoDoesNotExistException, InsufficientAmountException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		wsi.createAccount("nitin", "7351117073", new BigDecimal(1000));
		wsi.createAccount("manish", "9690095194", new BigDecimal(500));
		wsi.fundTransfer("7351117073","9690095194",new BigDecimal(200));
	}
	
	@Test(expected=com.Wallet.exception.InsufficientAmountException.class)
	public void WhenAmountEnteredIsGreaterThanTheAvailableBalanceWhileFundTransfer() throws DuplicateMobileNumberException, MobileNoDoesNotExistException, InsufficientAmountException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		wsi.createAccount("nitin", "7351117074", new BigDecimal(500));
		wsi.createAccount("rajat", "9690095196", new BigDecimal(1000));
		wsi.fundTransfer("7351117074","9690095196",new BigDecimal(600));
	}
	@Test
	public void WhenAmountEnteredForDepositIsCorrectAndSuccessfull() throws DuplicateMobileNumberException, MobileNoDoesNotExistException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		wsi.createAccount("nitin", "7351117076", new BigDecimal(500));
		wsi.depositAmount("7351117076", new BigDecimal(500));
	}
	
	@Test(expected=com.Wallet.exception.MobileNoDoesNotExistException.class)
	public void WhenEnteredMobileNoIsNotPresentWhenDepositingTheAmount() throws DuplicateMobileNumberException, MobileNoDoesNotExistException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		wsi.createAccount("nitin", "7351117077", new BigDecimal(500));
		wsi.depositAmount("7351117077", new BigDecimal(500));
	}

	@Test
	public void WhenEnteredDetailForWithdrawAmountIsCorrect() throws DuplicateMobileNumberException, MobileNoDoesNotExistException, InsufficientAmountException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		wsi.createAccount("nitin", "7351117078", new BigDecimal(500));
		wsi.withdrawAmount("7351117078", new BigDecimal(300));
	} 
	@Test(expected=com.Wallet.exception.InsufficientAmountException.class)
	public void WhenEnteredAmountIsGreaterThenAvailableAmount() throws DuplicateMobileNumberException, MobileNoDoesNotExistException, InsufficientAmountException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		wsi.createAccount("nitin", "7351117079", new BigDecimal(500));
		wsi.withdrawAmount("7351117079", new BigDecimal(700));
	}
	
}
