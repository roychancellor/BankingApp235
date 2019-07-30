/**
 * Bank is the primary class for the banking application, housing all methods to process user input
 * The actual account computations occur in the account classes Checking, Saving, and Loan
 */
package edu.gcu.cst235.milestone.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import edu.gcu.cst235.milestone.model.Account;
import edu.gcu.cst235.milestone.model.Customer;

public class Bank {

	//Class data
	//Scanner on System.in for use in all classes
	public static Scanner scanner = new Scanner(System.in);
	private String bankName;
	private List<Customer> customers = new ArrayList<Customer>();
	private int custIndex = 0;
	
	//Format for dates and money outputs in all classes
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	public static DecimalFormat money = new DecimalFormat();
	public static final String MONEY_FORMAT = "$#,##0.00;($#,##0.00)";

	//Class constants
	private static final int MENU_MIN = 1;
	private static final int MENU_MAX = 7;
	private static final int MENU_EXIT = 0;
	private static final boolean CUSTOMER_UPDATE = true;
	private static final boolean CUSTOMER_TRANSACT = false;
	
	//Constructor
	public Bank(String bankName) {
		this.bankName = bankName;
		/*** CREATE LIST OF CUSTOMERS FOR EASE OF TESTING ***/
		customers.add(new Customer("Tony", "Womack", new Date()));
		customers.add(new Customer("Craig", "Counsell", new Date()));
		customers.add(new Customer("Luis", "Gonzales", new Date()));
		customers.add(new Customer("Matt", "Williams", new Date()));
		customers.add(new Customer("Steve", "Finley", new Date()));
		customers.add(new Customer("Danny", "Bautista", new Date()));
		customers.add(new Customer("Mark", "Grace", new Date()));
		customers.add(new Customer("Damian", "Miller", new Date()));
		customers.add(new Customer("Curt", "Schilling", new Date()));
		
		//Sort the customers by lastName, firstName
		Collections.sort(customers);
		
		//Set the money format
		money.applyPattern(MONEY_FORMAT);
	}
	
	//Class methods
	
	/**
	 * Displays the highest level (main) menu and gets a user selection.
	 * If the user enters a non-integer, nextInt throws NumberFormatException
	 * which gets caught and calls method again
	 */
	public void viewMainMenu() {

		try {
			int option;
			do {
				System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("          MAIN MENU");
				System.out.println("          " + bankName);
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("\nPick an option: ");
				System.out.println("----------------------------");
				System.out.println(" 1 : Customer Management");
				System.out.println(" 2 : Customer Transactions");
				System.out.println("----------------------------");
				System.out.println(" " + MENU_EXIT + " : Exit Banking Application");
				//try to convert user input into an integer (throws InputMismatchException if not)
				option = scanner.nextInt();
				processMainMenu(option);
			} while (option != MENU_EXIT);
		}
		catch(Exception e) {  //generated by nextInt()
			printInputError(1, 2);
			//When a scanner throws an InputMismatchException, the scanner will not pass the token
			//that caused the exception, so that it may be retrieved or skipped via some other method.
			//So, read the token that caused the exception so it's not in the scanner anymore
			scanner.nextLine();
			//Re-call the menu method
			viewMainMenu();
		}
	}
	
	/**
	 * Calls the Create Customer menu or the Customer Selection Menu based on the option selected
	 * @param option
	 */
	private void processMainMenu(int option) {
		switch(option) {
			case 1:
				viewCustomerCreationMenu();
				break;
			case 2:
				viewCustomerSelectionMenu(CUSTOMER_TRANSACT);
				break;
			case MENU_EXIT:
				viewBankingAppExit();
				break;
			default:
				printInputError(1, 2);
				viewMainMenu();
		}
	}
	
	/**
	 * Displays the customer selection menu and gets a user selection.
	 * If the user enters a non-integer, nextInt throws NumberFormatException
	 * which gets caught and calls method again
	 */
	private void viewCustomerCreationMenu() {
		try {
			int option = 0;
			do {
				System.out.println("\n============================");
				System.out.println("    Customer Management");
				System.out.println("============================");
				System.out.println(" 1 : Enter New Customer");
				System.out.println(" 2 : Modify Customer");
				System.out.println("-------------------------");
				System.out.println(" " + MENU_EXIT + " : Return to Main Menu");
				//try to convert user input into an integer (throws InputMismatchException if not)
				option = scanner.nextInt();
				
				if(option != MENU_EXIT) {
					//Validate the selection is valid
					if(option == 1) {
						viewEnterCustomerInformation();
					}
					else if(option == 2) {
						viewCustomerSelectionMenu(CUSTOMER_UPDATE);
					}
					else
						printInputError(1, 2);
				}
			} while(option != MENU_EXIT);
		}
		catch(Exception e) {  //generated by nextInt()
			printInputError(1, 2);
			//When a scanner throws an InputMismatchException, the scanner will not pass the token
			//that caused the exception, so that it may be retrieved or skipped via some other method.
			//So, read the token that caused the exception so it's not in the scanner anymore
			scanner.nextLine();
			//Re-call the menu method
			viewCustomerCreationMenu();
		}
	}
	
	/**
	 * Gets customer first and last name and creates a new customer object
	 * Sorts the entire customer list
	 */
	private void viewEnterCustomerInformation() {
		//Clear the newline from last nextInt call
		scanner.nextLine();
		//Add a new Customer object to the existing list of customers
		customers.add(new Customer(getName("Enter first name:"), getName("Enter last name:"), new Date()));
		System.out.println("\nSuccess, "
			+ customers.get(customers.size() - 1).getFirstName()
			+ " " + customers.get(customers.size() - 1).getLastName()
			+ " created."
		);
		
		//Sort the list of Bank customers by lastName, firstName
		Collections.sort(customers);
	}
	
	/**
	 * updates customer first and last name
	 */
	private void updateCustomerInformation() {
		//Clear the newline from last nextInt call
		scanner.nextLine();
		
		//Get new names
		String origLastName = customers.get(custIndex).getLastName();
		String origFirstName = customers.get(custIndex).getFirstName();
		customers.get(custIndex).setFirstName(getName("Enter new first name:"));
		customers.get(custIndex).setLastName(getName("Enter new last name:"));
		System.out.println("\nSuccess, " + origFirstName + " " + origLastName
			+ " changed to "
			+ customers.get(custIndex).getFirstName()
			+ " " + customers.get(custIndex).getLastName()
		);
		//Sort the list of Bank customers by lastName, firstName
		Collections.sort(customers);
	}
	
	/**
	 * helper method for getting customer names
	 * @param message
	 * @return
	 */
	private String getName(String message) {
		System.out.println(message);		
		return scanner.nextLine();
	}
	
	/**
	 * Displays the customer selection menu and gets a user selection.
	 * If the user enters a non-integer, nextInt throws NumberFormatException
	 * which gets caught and calls method again
	 */
	private void viewCustomerSelectionMenu(boolean updateOrTransact) {
		try {
			int option = 0;
			do {
				System.out.println("\n==============================");
				System.out.println("   Customer Login");
				System.out.println("   Select Customer:");
				System.out.println("==============================");
				for(int i = 0; i < customers.size(); i++) {
					System.out.println(
						" " + (i + 1) + " : "
						+ customers.get(i).getFirstName()
						+ " " + customers.get(i).getLastName()
					);
				}
				System.out.println("------------------------");
				System.out.println(" " + MENU_EXIT + " : Return to Main Menu");
				//try to convert user input into an integer (throws InputMismatchException if not)
				option = scanner.nextInt();
				
				if(option != MENU_EXIT) {
					//Validate the selection is between 1 and the customer list length
					if(option >= 1 && option <= customers.size()) {
						custIndex = option - 1;
						if(updateOrTransact == CUSTOMER_TRANSACT) {
							welcomeCustomer();
							viewCustomerActionMenu();
						}
						else if(updateOrTransact == CUSTOMER_UPDATE) {
							updateCustomerInformation();
							option = MENU_EXIT;
						}
					}
					else
						printInputError(1, customers.size());
				}
			} while(option != MENU_EXIT);
		}
		catch(Exception e) {  //generated by nextInt()
			printInputError(1, customers.size());
			//When a scanner throws an InputMismatchException, the scanner will not pass the token
			//that caused the exception, so that it may be retrieved or skipped via some other method.
			//So, read the token that caused the exception so it's not in the scanner anymore
			scanner.nextLine();
			//Re-call the menu method
			viewCustomerSelectionMenu(updateOrTransact);
		}
	}
	
	/**
	 * prints a welcome message to the customer, verbose with loan details
	 */
	private void welcomeCustomer() {
		if(this.custIndex < customers.size())
			System.out.println(customers.get(custIndex).toString(false));
	}
	
	/**
	 * Displays the customer action menu and gets a user selection.
	 * If the user enters a non-integer, nextInt throws NumberFormatException
	 * which gets caught and calls method again
	 */
	private void viewCustomerActionMenu() {

		try {
			int option;
			do {
				System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("       CUSTOMER TRANSACTION MENU");
				System.out.println("                " + bankName);
				System.out.println("        Welcome " + customers.get(custIndex).getFirstName() + " " + customers.get(custIndex).getLastName() + "!");
				System.out.println("          " + dateFormat.format(new Date()));
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("\nPick an option: ");
				printHeaderLine(29);
				System.out.println(" 1 : Deposit to Checking");
				System.out.println(" 2 : Deposit to Savings");
				System.out.println(" 3 : Withdraw from Checking");
				System.out.println(" 4 : Withdraw from Savings");			
				System.out.println(" 5 : Make a Loan Payment");			
				System.out.println(" 6 : View Loan amortization");			
				System.out.println(" 7 : Get Account Balances");
				System.out.println(" 8 : Get Monthly Statement");
				printHeaderLine(29);
				System.out.println(" " + MENU_EXIT + " : Return to Customer Login");
				//try to convert user input into an integer (throws InputMismatchException if not)
				option = scanner.nextInt();
				processCustomerMenu(option);
			} while (option != MENU_EXIT);
		}
		catch(Exception e) {  //generated by nextInt()
			printInputError(MENU_MIN, MENU_MAX);
			//When a scanner throws an InputMismatchException, the scanner will not pass the token
			//that caused the exception, so that it may be retrieved or skipped via some other method.
			//So, read the token that caused the exception so it's not in the scanner anymore
			scanner.nextLine();
			//Re-call the menu method
			viewCustomerActionMenu();
		}
	}
	
	private void printInputError(int lower, int upper) {
		System.out.println("\n** Oops, please enter a number from " + lower + " to " + upper + " or " + MENU_EXIT + " to Logout\n");		
	}

	/**
	 * Calls a method to display the screen to process the user-selected option from the main menu
	 * After each transaction, calls viewBalances to update the user
	 * @param option
	 */
	private void processCustomerMenu(int option) {

		switch(option) {
		case 1:
			customers.get(custIndex).getChecking().doTransaction(
				Account.DEPOSIT,
				customers.get(custIndex).getChecking().getTransactionValue(Account.AMOUNT_MESSAGE + "deposit: ")
			);
			viewBalances();
			break;
		case 2:
			customers.get(custIndex).getSaving().doTransaction(
				Account.DEPOSIT,
				customers.get(custIndex).getSaving().getTransactionValue(Account.AMOUNT_MESSAGE + "deposit: ")
			);
			viewBalances();
			break;
		case 3:
			customers.get(custIndex).getChecking().doTransaction(
				Account.WITHDRAWAL,
				customers.get(custIndex).getChecking().getTransactionValue(Account.AMOUNT_MESSAGE + "withdraw: ")
			);
			viewBalances();
			break;
		case 4:
			customers.get(custIndex).getSaving().doTransaction(
				Account.WITHDRAWAL,
				customers.get(custIndex).getSaving().getTransactionValue(Account.AMOUNT_MESSAGE + "withdraw: ")
			);
			viewBalances();
			break;
		case 5:
			System.out.println("\nYour minimum monthly payment is "
				+ money.format(customers.get(custIndex).getLoan().getMonthlyPaymentAmount()));
			customers.get(custIndex).getLoan().doTransaction(
				customers.get(custIndex).getLoan().getTransactionValue(Account.AMOUNT_MESSAGE + "pay on the loan: ")
			);
			viewBalances();
			break;
		case 6:
			customers.get(custIndex).getLoan().viewAmortization();
		case 7:
			viewBalances();
			break;
		case 8:
			viewEndOfMonth();
			break;  
		case MENU_EXIT:
			viewCustomerExit();
			break;
		default:
			printInputError(MENU_MIN, MENU_MAX);
			viewCustomerActionMenu();
		}
	}
	
	/**
	 * Displays all account balances
	 */
	private void viewBalances() {
		System.out.println(customers.get(custIndex).toString(false));
	}
	
	/**
	 * Shows the end of month screen and performs the end-of-month calculations
	 */
	private void viewEndOfMonth() {

		System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("                                 GCU BANK");
		System.out.println("                         END OF MONTH STATEMENT");
		System.out.println("                       for customer " + customers.get(custIndex).getFirstName() + " " + customers.get(custIndex).getLastName());
		System.out.println("                          " + dateFormat.format(new Date()));
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		//Determine if the end of the month has occurred
		//Stub-out only - NOT IMPLEMENTED IN THIS VERSION
		boolean endOfMonth = true;
		if(endOfMonth) {
			System.out.println("\nMonthly charges and credits:");
			printHeaderLine(65);
			customers.get(custIndex).getSaving().doEndOfMonth();
			customers.get(custIndex).getLoan().doEndOfMonth();
			
			//Display the transaction list
			System.out.println("\nDate and Time\t\tAccount\t\tAmount\t\tDescription");
			printHeaderLine(75);
			customers.get(custIndex).getChecking().displayTransactions();
			printHeaderLine(75);
			customers.get(custIndex).getSaving().displayTransactions();
			printHeaderLine(75);
			customers.get(custIndex).getLoan().displayTransactions();
		}
		else {
			System.out.println("\nSorry, the <current month> is not complete.");
		}
	}		

	/**
	 * Outputs a message to the customer when exiting the customer transaction menu
	 */
	private void viewCustomerExit() {
		System.out.println("\nGoodbye " + customers.get(custIndex).getFirstName() + ". Have a good day!\n");
	}

	/**
	 * Outputs a message to the banker when exiting the banking application completely
	 */
	private void viewBankingAppExit() {
		System.out.println("\nGoodbye banker. Application closed at " + dateFormat.format(new Date()) + ". Have a good day!\n");
	}
	
	/**
	 * Prints a series of dashes for use as a header underline
	 * @param numDashes the number of dashes to print in a single line
	 */
	public static void printHeaderLine(int numDashes) {
		for(int i = 0; i < numDashes; i++)
			System.out.print("-");
		System.out.println();
	}
}