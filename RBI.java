package Banking_Management;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class RBI {

	public static void main(String[] args) {
		System.out.println("******	:  Welcome bank to the Bank Management :  ******\n");
		System.out.println("Do you want open a banck account : 1.Yes 2. No");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if (choice == 1) {
			OpenAccount op = new OpenAccount();
			op.CreateAccount();
		}
		if (choice == 2) {
			System.out.println("\n--> Thank you for choosing Us...");
		// calculateInterest(5000);
	}
}
}

class OpenAccount {

	String name;
	int AccountNumber;
	String AccountType;
	String DOB;
	String Bank;

	public void CreateAccount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("In which bank you want to open account : 1.IOB    2.SBI    3.HDFC");
		int bankchoice = sc.nextInt();
		if (bankchoice == 1) {
			Bank = "IOB";
		}
		if (bankchoice == 2) {
			Bank = "SBI";
		}
		if (bankchoice == 3) {
			Bank = "HDFC";
		}
		System.out.println("Enter you Name ;");
		sc.nextLine();
		name = sc.nextLine();

		System.out.println("Enter the date of birth :\n");
		DOB = sc.nextLine();
		System.out.println("What type of account you have : 1. Saving 2. Current");
		int choice = sc.nextInt();
		if (choice == 1) {
			AccountType = "Saving";
		}
		if (choice == 2) {
			AccountType = "Current";
		}

		System.out.println("Your Account details are : ");
		System.out.println("***************************\n");
		System.out.println("Name of the Bank = " + Bank);
		System.out.println("Name of the Account Holder : " + name);
		System.out.println("Date of Birth = " + DOB);
		System.out.println("Type of Account : " + AccountType);
		System.out.println("Account Number is : " + Math.random());
		System.out.println("\n");

		BankAccount obj = new BankAccount();
		obj.showMenu('A');
		sc.close();
	}

	class BankAccount {
		int balance;
		int previousBalance;
		String customerName;
		String accountType;
		double totalInterest;

		void calculateInterest(double balance) {
			System.out.println("What type of account you have : 1. Saving 2. Current");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 1) {
				AccountType = "Saving";
				int r = 5;
				int t;
				System.out.println("Enter numbers of years to calculete the Interest : ");
				t = sc.nextInt();
				totalInterest = (balance * r * t)/100;
				double totalAmount = totalInterest + balance;
				System.out.println("Total Interest = " + totalInterest);
			}
			if (choice == 2) {
				AccountType = "Current";
				int r = 8;
				int time ;
				System.out.println("Enter numbers of years to calculete the Interest : ");
				time = sc.nextInt();
				double totalAmount = balance * Math.pow(1+ r /100, time);
				totalInterest = totalAmount - balance;
				System.out.println("Compound Interest = " + totalInterest);
				sc.close();
			}

		}

		void deposit(int amount) {
			if (amount != 0) {
				balance = balance + amount;
				System.out.println("Balance after Deposit = " + balance);
				previousBalance = amount;
			}
		}

		void withdraw(int amount) {
			if (amount != 0) {
				balance = balance - amount;
				System.out.println("Balance after Withdraw = " + balance);
				previousBalance = (-amount);
			}
		}

		void getPreviousBalance() {
			FileOutputStream out;
			PrintStream input;
			try {
				out = new FileOutputStream("/Applications/Eclipse.app/Contents/MacOS/Challenge_6/Source2.txt");
				input = new PrintStream(out);

				if (previousBalance > 0) {
					input.append("Deposited : " + previousBalance);
					System.out.println("Deposited : " + previousBalance);
				} else if (previousBalance < 0) {
					input.append("Withdrawn : " + previousBalance);
					System.out.println("Withdrawn : " + Math.abs(previousBalance));
				} else {
					System.out.println("No Transactions yet...");
				}
				input.close();
			} catch (Exception e) {
				System.err.println("Error in printing the data " + e);
			}
		}

		void showMenu(char option) {
			System.out.println("****** : Welcome to Banking Menu : ******");
			System.out.println("A.Balance");
			System.out.println("B.Deposite");
			System.out.println("C.Withdraw");
			System.out.println("D.Previous Balance");
			System.out.println("E.Total Interest");
			System.out.println("F.Exit");
			Scanner in = new Scanner(System.in);
			
			do {
				System.out.println("******************************");
				System.out.println("Enter an option ");
				System.out.println("******************************");
				System.out.println("\n");
				option = in.next().charAt(0);

				switch (option) {

				case 'A':
					System.out.println("***************************");
					System.out.println("Balance = " + balance);
					System.out.println("\n");
					break;

				case 'B':
					System.out.println("***************************");
					System.out.println("Enter amount to deposit : ");
					int amount = in.nextInt();
					deposit(amount);
					System.out.println("\n");
					break;

				case 'C':
					System.out.println("***************************");
					System.out.println("Withdraw Amount :");
					int Amount = in.nextInt();
					withdraw(Amount);
					System.out.println("\n");
					break;

				case 'D':
					System.out.println("***************************");
					// System.out.println("Balance = " + balance);
					getPreviousBalance();
					System.out.println("\n");
					break;
				case 'E':
					System.out.println("***************************");
					// System.out.println("Balance = " + balance);
					calculateInterest(balance);
					System.out.println("\n");
					break;
				case 'F':
					System.out.println("***************************");
					break;
				default:
					System.out.println("Invalid choice");
				}

			} while (option != 'F');
			{
				System.out.println("Thank you, Visit Again");
				in.close();
			}
		}
	}
}

