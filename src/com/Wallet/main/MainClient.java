package com.Wallet.main;
import java.util.Scanner;
import java.math.BigDecimal;

//import com.Wallet.bean.Wallet;
import com.Wallet.exception.DuplicateMobileNumberException;
import com.Wallet.exception.InsufficientAmountException;
import com.Wallet.exception.MobileNoDoesNotExistException;
import com.Wallet.repo.WalletRepoImpl;
import com.Wallet.service.WalletServiceImpl;

public class MainClient {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws DuplicateMobileNumberException, MobileNoDoesNotExistException, InsufficientAmountException {
		WalletRepoImpl wri=new WalletRepoImpl();
		WalletServiceImpl wsi=new WalletServiceImpl(wri);
		String name;
		String phoneNo;
		BigDecimal amount;
		String sourcePhoneNo;
		String targetPhoneNo;
		while(true) {
		System.out.println("1.Create Account");
		System.out.println("2.Show Balance");
		System.out.println("3.FundTransfer");
		System.out.println("4.Deposit Amount");
		System.out.println("5.Withdraw Amount");
		System.out.println("6.Exit");
	    System.out.println("");
		System.out.println("Enter Choice");
		int ch=sc.nextInt();
		sc.nextLine();
		switch(ch)
		{
		case 1: System.out.println("Enter Name");
				name=sc.nextLine();
				validateName(name);
				System.out.println("Enter Phone Number");
				phoneNo=sc.nextLine();
				validatePhoneNo(phoneNo);
				System.out.println("Enter Amount to deposit");
				amount=sc.nextBigDecimal();
				validateAmount(amount);
				System.out.println(wsi.createAccount(name,phoneNo, amount));
				break;
				
		case 2: System.out.println("Enter Phone Number");
				phoneNo=sc.nextLine();
				System.out.println(wsi.showBalance(phoneNo));
				break;
		
		
		case 3: System.out.println("Enter Source Phone Number");
				sourcePhoneNo=sc.nextLine();
				System.out.println("Enter Target Phone Number");
				targetPhoneNo=sc.nextLine();
				validatefundTransfer(sourcePhoneNo,targetPhoneNo);
				System.out.println("Enter Amount");
				amount=sc.nextBigDecimal();
				validateAmount(amount);
				System.out.println(wsi.fundTransfer(sourcePhoneNo, targetPhoneNo, amount));
				break;
				
		case 4: System.out.println("Enter Phone Number");
				phoneNo=sc.nextLine();
				System.out.println("Enter Amount");
				amount=sc.nextBigDecimal();
				validateAmount(amount);
				System.out.println(wsi.depositAmount(phoneNo, amount));
				break;
		
		case 5: System.out.println("Enter Phone Number");
				phoneNo=sc.nextLine();
				System.out.println("Enter Amount");
				amount=sc.nextBigDecimal();
				validateAmount(amount);
				System.out.println(wsi.withdrawAmount(phoneNo, amount));
				break;		
		case 6: System.exit(0);
		
		default:System.out.println("Wrong choice");
		}
		
		}
		
		
	}
	public static void validatefundTransfer(String sourcePhoneNo, String targetPhoneNo) {
		if(sourcePhoneNo.equals(targetPhoneNo)) {
			System.out.println("Source phone number and target phone number can not be same");
			System.out.println("Enter Source Phone Number");
			sourcePhoneNo=sc.nextLine();
			System.out.println("Enter Target Phone Number");
			targetPhoneNo=sc.nextLine();
			validatefundTransfer(sourcePhoneNo, targetPhoneNo);}
		}
	
	public static String validateName(String name) {
		if(name.equals(null) && name.equals(" ")) {
			System.out.println("Name Field can not be empty");
			System.out.println("Again Enter name");
			name=sc.nextLine();
			validateName(name);
		}
		if(name.matches("[A-Z,a-z ]+")==false) {
			System.out.println("Name Field only contain character");
			System.out.println("Enter valid name");
			name=sc.nextLine();
			validateName(name);
		}
		return name;
		
		}
	public static String validatePhoneNo(String phoneNo) {
		if(phoneNo.length()!=10) {
			System.out.println("Enter 10 digit phone No.");
			phoneNo=sc.nextLine();
			validatePhoneNo(phoneNo);
		}
		if(phoneNo.matches("[0-9]+")==false) {
			System.out.println("Enter Only numbers");
			System.out.println("Again Enter phone no");
			phoneNo=sc.nextLine();
			validatePhoneNo(phoneNo);
			}
		return phoneNo;
		
	}
	
	public static BigDecimal validateAmount(BigDecimal amount) {
		if(amount==null) {
			System.out.println("Amount Field can not be empty");
			System.out.println("Again Enter Amount");
			amount=sc.nextBigDecimal();
			validateAmount(amount);
		}
		if(amount.compareTo(new BigDecimal(0))<= 0) {
			System.out.println("Amount should be more than Zero");
			System.out.println("Enter Valid Amount");
			amount=sc.nextBigDecimal();
			validateAmount(amount);
			}
		
		
		return amount;	
	}

}
