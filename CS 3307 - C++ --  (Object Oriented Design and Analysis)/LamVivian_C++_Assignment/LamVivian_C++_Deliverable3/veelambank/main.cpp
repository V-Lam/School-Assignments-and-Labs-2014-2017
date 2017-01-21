//***************************************************************
//Vivian Lam
//Oct 24 2016
//CS3307
//***************************************************************



//***************************************************************
//                   HEADER FILE USED IN PROJECT
//****************************************************************

#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>
#include <limits>
#include <algorithm>
#include <stdio.h>
#include <time.h> 
#include <fstream>
#include "Manager.h"
#include "Customer.h"
#include "Maitenance.h"


using namespace std;


//***************************************************************
//    	function declaration
//****************************************************************


//Manager
void managerMenu(vector<Customer>& Customers);					 // Brings user to the manager screen
bool createAccount(vector<Customer>& Customers, int accID, string firstName, string lastName);//creates an account
bool deleteAccount(vector<Customer>& Customers, int accID); //deletes an account
void printManMenu(void);									//Prints manager menu

//Maintenance
void maintenanceMenu(void);		 // Brings user to the maintenance screen
void exTraceSwitch(void);   // turns on/off execution trace
void setExTrace(bool x);   // Turns execution traces on or off depending if it's already on or off
void writeToET(string str);  // Writes str to the file execution_traces.txt 
void printExecutionTraces(void);//prints execution traces
void clearExecutionTracesLog(void);//clears execution traces
void saveExternals(Manager manager, Maitenance maintain);//saves

//Customer
void CustomerMenu(Customer& theCustomer, vector<Customer>& Customers); //Brings user to the screen for Customers
void printCustomerMenu(Customer& theCustomer, bool cheq, bool sav);// Prints the options available to the Customer
void deposit(Customer& theCustomer);
void withdraw(Customer& theCustomer);
void withdrawChequing(Customer& theCustomer);
void withdrawSavings(Customer& theCustomer);
void transfer(Customer& theCustomer);
void transferChequing(Customer& theCustomer);
void transferSavings(Customer& theCustomer);
void chequing(Customer& theCustomer, bool cheq, bool sav);
void savings(Customer& theCustomer, bool sav, bool cheq);

//Helper functions
int getNumber();        // Retrieves an int value from the user
double getDouble();     // Retrieves a double value from the user
void saveCustomers(vector<Customer>& Customers);					 // Writes Customer data to text file/ saves the changes
int getAccountIndex(vector<Customer>& Customers, int targetAccNum, int low, int high);
bool compareAccountLastName(Customer a, Customer b);
bool compareAccountNums(Customer a, Customer b);
bool existsInDatabase(vector<Customer>& Customers, int targetAccID);
void sortByAccountNumber(vector<Customer>& Customers);
bool penalty(Customer& theCustomer, double amount);

//other
void press_enter();
void intro();	//introductory screen function
void cool_ascii();//used for intro


//***************************************************************
//    	FIELDS
//****************************************************************

// Global Variables
bool executionTraces; // This is defined main().
stringstream et;
int endOfMonth;	// The end of the month for credit

//***************************************************************
//    	THE MAIN FUNCTION OF PROGRAM
//****************************************************************
int main()
{
	et << "main():\n"; writeToET(et.str()); et.str("");
	cout.precision(2);
	srand(time(NULL));

	// Initialize executionTraces.
	// Read from the file execution_traces.txt and check the first line.  The first line saves the state of whether the ET is on/off.
	// 1 = ON, 0 = OFF
	ifstream etFile("execution_traces.txt");
	if (etFile.is_open()) {
		// Read the file's first line.
		string etStr;
		getline(etFile, etStr);
		if (etStr.compare("1") == 0) {  // If 1, then on
			executionTraces = true;
		}
		else if (etStr.compare("0") == 0) {  // If 0, then off
			executionTraces = false;
		}
		else {  // If neither, then off and error message
			executionTraces = false;
			cout << "Error: Could not read current state of execution traces. Execution traces are OFF by default.\n";
		}

	}
	else {
		cout << "Could not open execution_traces.txt. Execution traces are OFF by default.\n";
		executionTraces = false;
	}

	// initialize variables
	const int managerId = NULL;
	const int maintenId = NULL;
	vector<Customer> Customers;								//Customer vector array
	int id, i = 0, getId, pin;;
	double chequings, savings, credLimit, cred, limit, owed;
	string line, name1, name2, label;
	Customer *f1;											//pointer to the customer array
	ifstream myfile("Customers.txt");						//list of customers


	// Read Customer info from text file into vector array
	if (myfile.is_open()) {
		while (getline(myfile, line))
		{
			stringstream ss(line);
			ss >> id >> chequings >> savings >> name1 >> name2 >> pin >> limit;
			f1 = new Customer;
			f1->setId(id);
			f1->setChequing(chequings);
			f1->setSavings(savings);
			f1->setFirstName(name1);
			f1->setLastName(name2);
			f1->setPin(pin);
			f1->setCredLimit(limit);
			Customers.push_back(*f1);
			i++;
		}
		myfile.close();
	}
	else {
		cout << "Error: Customer file not found\n";
	}
	
	Maitenance maintainPerson;
	Manager manager;
	ifstream maintainFile("externals.txt");

	// Read external info from text file;
	if (maintainFile.is_open()) {
		// Get maintainance person ID from file
		getline(maintainFile, line);
		stringstream ss(line);
		ss >> label >> id;
		maintainPerson.setID(id);

		// Get manager ID from file
		getline(maintainFile, line);
		stringstream ss2(line);
		ss2 >> label >> id;
		manager.setID(id);

		// Get end of month data from file
		getline(maintainFile, line);
		stringstream ss3(line);
		ss3 >> label >> id;
		endOfMonth = id;
		// Close file
		myfile.close();

	}
	else {
		cout << "Error: Externals file not found\n";
	}

	//save
	saveExternals(manager, maintainPerson);


	intro();//summons the intro

	while (true) {					//prompts for login. will check what type of account corresponds to login
		bool found = false;
		while (!found) {
			cout << endl << "Please enter your login ID: ";
			getId = getNumber();
			if (getId == manager.getID()) {
				managerMenu(Customers);
				found = true;
			}
			else if (getId == maintainPerson.getID()) {
				maintenanceMenu();
				found = true;
			}
			else if (existsInDatabase(Customers, getId)) {
				int x = getAccountIndex(Customers, getId, 0, Customers.size() - 1);
				CustomerMenu(Customers[x], Customers);
				found = true;
			}
			if (!found)
				cout << endl << "ID invalid!!\n";

		}
	}

	et << "End of main()\n"; writeToET(et.str()); et.str("");//writes to the trace
	return 0;
}

void CustomerMenu(Customer& theCustomer, vector<Customer>& Customers) {
	et << "Logged in as Customer #" << theCustomer.getId() << "\n"; writeToET(et.str()); et.str("");
	cout << endl << "Welcome " << theCustomer.getFirstName() << "!\n";
	bool cheq, sav, logout = false;
	while (!logout) {
		if (theCustomer.getChequing() == 0) { cheq = false; }
		else { cheq = true; }
		if (theCustomer.getSavings() == 0) { sav = false; }
		else { sav = true; }

		printCustomerMenu(theCustomer, cheq, sav);

		switch (getNumber()) {
		case 1:
			et << theCustomer.getId() << " selected 1: Chequing\n"; writeToET(et.str()); et.str("");
			chequing(theCustomer, cheq, sav);
			saveCustomers(Customers);
			break;
		case 2:
			et << theCustomer.getId() << " selected 2: Savings\n"; writeToET(et.str()); et.str("");
			savings(theCustomer, sav, cheq);
			saveCustomers(Customers);
			break;
		case 3:
			et << theCustomer.getId() << " selected 3: Deposit\n"; writeToET(et.str()); et.str("");
			deposit(theCustomer);
			saveCustomers(Customers);
			break;
		case 4:
			et << theCustomer.getId() << " selected 4: Withdraw\n"; writeToET(et.str()); et.str("");
			withdraw(theCustomer);
			saveCustomers(Customers);
			break;
		case 5:
			et << theCustomer.getId() << " selected 5: View balance\n"; writeToET(et.str()); et.str("");
			if (cheq) {
				cout << endl << "Chequings Account: $" << fixed << theCustomer.getChequing() << "\n";
			}if (sav) {
				cout << endl << "Savings Account:   $" << fixed << theCustomer.getSavings() << "\n";
			}
			if (!sav && !cheq) {
				cout << endl << "You don't have any accounts open. Please open an account first\n";
			}
			break;
		case 6:
			et << theCustomer.getId() << " selected 6: Transfer\n"; writeToET(et.str()); et.str("");//writes to the trace
			transfer(theCustomer);
			saveCustomers(Customers);
			break;
		case 7://logout
			cout << "\n\n\tThanks for using bank managemnt system\n";
			et << theCustomer.getId() << " selected 7: Logout\n"; writeToET(et.str()); et.str("");//writes to the trace
			intro();
			logout = true;
			break;			
		default:
			et << theCustomer.getId() << " entered invalid input\n"; writeToET(et.str()); et.str("");		//writes to the trace
			cout << "Invalid input \n";
		}
	}
}



void chequing(Customer& theCustomer, bool cheq, bool sav) {
	bool done = false;
	double chequing = theCustomer.getChequing();
	while (!done) {
		done = true;
		if (cheq == false) { //open chequing account
			cout << endl << "You have opened your chequing account!";
			cout << " How much would you like to deposit?\n";
			double x = getDouble();
			theCustomer.setChequing(x);
			et << theCustomer.getId() << " opened a chequing account with $" << x << "\n"; writeToET(et.str()); et.str("");
			cout << endl << "Successfully deposited $" << fixed << x << " into chequing account\n";
		}
		else {
			et << theCustomer.getId() << " is closing his/her chequing account.\n"; writeToET(et.str()); et.str("");
			cout << endl << "You have closed your chequing account.\n";
			if (sav) {
				cout << " Would you like to transfer the money into your savings account?\n";
				cout << "1: Yes\n";
				cout << "2: No\n";

				switch (getNumber()) {
				case 1: //Transfer money from chequings to savings
					et << theCustomer.getId() << " selected 1: Yes, transfer to savings\n"; writeToET(et.str()); et.str("");
					theCustomer.setSavings(theCustomer.getSavings() + chequing);
					cout << endl << "Successfully transfered $" << fixed << chequing << " into savings account\n";
					break;
				case 2: // Do not transfer
					et << theCustomer.getId() << " selected 2: No, do not transfer\n"; writeToET(et.str()); et.str("");
					break;
				default: //invalid handler
					et << theCustomer.getId() << " entered invalid input\n"; writeToET(et.str()); et.str("");
					cout << endl << "Invalid input.\n";
					done = false;
				}
			}
			cout << "\n";
			theCustomer.setChequing(0);
			et << "Chequing set to 0.\n"; writeToET(et.str()); et.str("");
		}
	}
}

void savings(Customer& theCustomer, bool sav, bool cheq) {
	bool done = false;
	double saving = theCustomer.getSavings();
	while (!done) {
		done = true;
		if (sav == false) { //if the savings account is closed
			et << theCustomer.getId() << " is opening a savings account.\n"; writeToET(et.str()); et.str("");
			cout << endl << "You have opened your savings account!";
			cout << " How much would you like to deposit?\n";
			double x = getDouble();
			theCustomer.setSavings(x);
			cout << endl << "Successfully deposited $" << fixed << x << " into Savings account\n";
			et << theCustomer.getId() << " opened a savings account with $" << fixed << x << "\n"; writeToET(et.str()); et.str("");
		}
		else { //if the savings account is open
			et << theCustomer.getId() << " is closing his/her savings account.\n"; writeToET(et.str()); et.str("");
			cout << endl << "You have closed your savings account.";
			if (cheq) {
				cout << " Would you like to transfer the money into your chequing account?\n";
				cout << "1: Yes\n";
				cout << "2: No\n";

				switch (getNumber()) {

				case 1: // Transfer from savings to chequings
					et << theCustomer.getId() << " selected 1: Transfer to chequing\n"; writeToET(et.str()); et.str("");
					theCustomer.setChequing(theCustomer.getChequing() + saving);
					cout << endl << "Successfully transfered $" << fixed << saving << " into chequing account\n";
					break;
				case 2: // Do not transfer
					et << theCustomer.getId() << " selected 2: Do not transfer to chequing\n"; writeToET(et.str()); et.str("");
					break;
				default: // invalid
					cout << endl << "Invalid input.\n";
					done = false;
				}
			}
			cout << "\n";
			theCustomer.setSavings(0);
			et << "Savings account set to $0\n"; writeToET(et.str()); et.str("");
		}
	}
}
void transfer(Customer& theCustomer) {
	bool done = false;
	while (!done) {
		cout << endl << "Which account would you like to transfer from?\n";
		cout << "1: CHEQUING ACCOUNT\n";
		cout << "2: SAVINGS ACCOUNT\n";
		cout << "3: CANCEL";
		switch (getNumber()) {
		case 1:
			et << theCustomer.getId() << " selected 1: Transfer from Chequing\n"; writeToET(et.str()); et.str("");
			transferChequing(theCustomer);
			return;
		case 2:
			et << theCustomer.getId() << " selected 2: Transfer from Savings\n"; writeToET(et.str()); et.str("");
			transferSavings(theCustomer);
			return;
		case 3:
			et << theCustomer.getId() << " selected 3: Cancel\n"; writeToET(et.str()); et.str("");
			return;
		default:
			et << theCustomer.getId() << " entered invalid input\n"; writeToET(et.str()); et.str("");
			cout << endl << "Error: Invalid input\n";
		}
	}


}
void deposit(Customer& theCustomer) {
	bool done = false;
	double x;
	while (!done) {
		done = true;
		cout << endl << "Which account would you like to deposit in?\n";
		cout << "1: CHEQUING ACCOUNT\n";
		cout << "2: SAVINGS ACCOUNT\n";
		cout << "3: CANCEL";

		switch (getNumber()) {

		case 1:  // Deposit into chequing account
			et << theCustomer.getId() << " selected 1: Deposit in Chequing\n"; writeToET(et.str()); et.str("");
			cout << endl << "How much would you like to deposit?\n";
			x = getDouble();
			theCustomer.setChequing(theCustomer.getChequing() + x);
			cout << endl << "Deposited $" << fixed << x << " into Chequing Account\n";
			et << theCustomer.getId() << " deposited $" << fixed << x << " in to his/her chequing\n"; writeToET(et.str()); et.str("");

			break;
		case 2:     // Deposit into savings account
			et << theCustomer.getId() << " selected 2: Deposit in Savings\n"; writeToET(et.str()); et.str("");
			cout << endl << "How much would you like to deposit?\n";
			x = getDouble();
			theCustomer.setSavings(theCustomer.getSavings() + x);
			cout << endl << "Deposited $" << fixed << x << " into Savings Account\n";
			et << theCustomer.getId() << " deposited $" << fixed << x << " in to his/her savings\n"; writeToET(et.str()); et.str("");
			break;
		case 3:     // Cancel
			et << theCustomer.getId() << " canceled.\n"; writeToET(et.str()); et.str("");
			return;
		default:    // Invalid input
			et << theCustomer.getId() << " entered invalid input\n"; writeToET(et.str()); et.str("");
			cout << "Invalid input\n";
			done = false;
		}
	}
}
void withdraw(Customer& theCustomer) {
	et << "withdraw(Customer& theCustomer #:" << theCustomer.getId() << ")\n"; writeToET(et.str()); et.str("");
	bool done = false;
	while (!done) {
		cout << endl << "***********---Which account would you like to withdraw from?---***********\n";
		cout << "---1: Chequing Account---\n";
		cout << "---2: Savings Account----\n";
		cout << "---3: Cancel-------------\n";
		switch (getNumber()) {
			//Withdraw from chequing account.
		case 1: {
			et << theCustomer.getId() << " selected 1: Withdraw from Chequing\n"; writeToET(et.str()); et.str("");
			withdrawChequing(theCustomer);
			return;
		}
				// Withdraw from savings account.
		case 2: {
			et << theCustomer.getId() << " selected 2: Withdraw from Savings\n"; writeToET(et.str()); et.str("");
			withdrawSavings(theCustomer);
			return;
		}
				// Cancel.
		case 3: {
			et << theCustomer.getId() << " canceled\n"; writeToET(et.str()); et.str("");
			return;
		}
		default: {
			et << theCustomer.getId() << " entered invalid input\n"; writeToET(et.str()); et.str("");
			cout << endl << "Error: Invalid input\n";
		}

		}

	}
}

void transferChequing(Customer& theCustomer) {
start:
	cout << endl << "How much would you like to transfer?\n";
	cout << "Enter 0 to cancel.\n";
	double x = getDouble();
	//Ask user for how much they want to transfer
	//Cancel
	if (x == 0) {
		et << theCustomer.getId() << " entered 0 to cancel\n"; writeToET(et.str()); et.str("");
		return;
	}//if the Customer tries to transfer more than they have 
	else if (x > theCustomer.getChequing()) {
		et << theCustomer.getId() << " tried to transfer too much money\n"; writeToET(et.str()); et.str("");
		cout << endl << "Error: Insufficient funds. You have $" << fixed << theCustomer.getChequing() << " in your chequing account.\n";
		goto start;
	}
	else {
		et << theCustomer.getId() << " entered a potential valid transfer amount\n"; writeToET(et.str()); et.str("");
		// Check if this puts the user under $1000 in the chequing account and apply a penalty if this is true.
		if (penalty(theCustomer, x)) { // There is a penalty.
			et << theCustomer.getId() << " faces a penalty for the withdrawal\n"; writeToET(et.str()); et.str("");
		warning:
			// Warn the user if they want to proceed with the withdrawal.
			cout << "Because this transaction will bring your chequing account's balance below $1000.00, a\n";
			cout << "$2.00 fee will applied. Do you wish to continue?\n";
			cout << "1: Yes\n";
			cout << "2: No\n";
			switch (getNumber()) {
			case 1:
			{
				et << theCustomer.getId() << " accepted the penalty\n"; writeToET(et.str()); et.str("");
				// Go through with the transaction.
				// Check if the $2.00 penalty will put the balance below $0.
				if (theCustomer.getChequing() - x - 2.00 < 0) {
					et << theCustomer.getId() << " does not have enough balance to accept the penalty\n"; writeToET(et.str()); et.str("");
					cout << endl << "Error: Insufficient funds. You have $" << fixed << theCustomer.getChequing() << " in your chequing account.\n";
					goto start;
				}
				else {
					// Perform the transaction.
					theCustomer.setChequing(theCustomer.getChequing() - x - 2.00);
					theCustomer.setSavings(theCustomer.getSavings() + x);
					cout << "Successfully transferred $" << fixed << x << " from your chequing account to your savings account.\n";
					cout << "Your chequing balance is $" << fixed << theCustomer.getChequing() << endl;
					cout << "Your savings balance is $" << fixed << theCustomer.getSavings() << endl;
					et << theCustomer.getId() << " successfully transferred with a penalty\n"; writeToET(et.str()); et.str("");
					return;
				}
				break;
			}
			case 2:
			{
				et << theCustomer.getId() << " cancelled the withdrawal\n"; writeToET(et.str()); et.str("");
				// Cancel the transaction and exit the withdraw menu.
				cout << "Transfer canceled. You have not been charged.\n";
				return;
			}
			default:
			{
				et << theCustomer.getId() << " entered invalid input\n"; writeToET(et.str()); et.str("");
				cout << endl << "Error: Invalid input\n";
				goto warning;
			}
			}
		}
		else { // No penalty.
			theCustomer.setChequing(theCustomer.getChequing() - x);
			theCustomer.setSavings(theCustomer.getSavings() + x);
			cout << endl << "Successfully transferred $" << fixed << x << " from chequing account\n";
			cout << "Your current chequing balance is $" << fixed << theCustomer.getChequing() << endl;
			cout << "Your current savings balance is $" << fixed << theCustomer.getSavings() << endl;
			et << theCustomer.getId() << " transferred $" << fixed << x << " without a penalty from chequing to savings\n"; writeToET(et.str()); et.str("");
		}

	}
}

void transferSavings(Customer& theCustomer) {
	cout << endl << "How much would you like to withdraw?\n";
	cout << "Enter 0 to cancel.\n";
	double amount;
	amount = getDouble();
	if (amount == 0) {
		et << theCustomer.getId() << " entered 0 to cancel\n"; writeToET(et.str()); et.str("");
		return;
	}
	else if (amount > theCustomer.getSavings()) {
		et << theCustomer.getId() << " tried to transfer too much money\n"; writeToET(et.str()); et.str("");
		cout << endl << "Error: Insufficient funds. You have $" << fixed << theCustomer.getSavings() << " in your Savings account\n";
		return;
	}
	theCustomer.setChequing(theCustomer.getChequing() + amount);
	theCustomer.setSavings(theCustomer.getSavings() - amount);
	cout << "Successfully transfered $" << fixed << amount << " from Savings account to Chequing Account\n";
	cout << "Your current chequing balance is $" << fixed << theCustomer.getChequing() << endl;
	cout << "Your current savings balance is $" << fixed << theCustomer.getSavings() << endl;
	et << theCustomer.getId() << " successfully transferred $" << fixed << amount << " from savings to chequing\n"; writeToET(et.str()); et.str("");

}

void withdrawChequing(Customer& theCustomer) {
start:
	cout << endl << "How much would you like to withdraw?\n";
	cout << "Enter 0 to cancel.\n";
	double x = getDouble();
	if (x == 0) {
		et << theCustomer.getId() << " entered 0 to cancel\n"; writeToET(et.str()); et.str("");
		return;
	}
	else if (x > theCustomer.getChequing()) {
		et << theCustomer.getId() << " tried to withdraw too much money\n"; writeToET(et.str()); et.str("");
		cout << endl << "Error: Insufficient funds. You have $" << theCustomer.getChequing() << " in your chequing account.\n";
		goto start;
	}
	else {
		// Check if this puts the user under $1000 in the chequing account and apply a penalty if this is true.
		if (penalty(theCustomer, x)) { // There is a penalty.
			et << theCustomer.getId() << " will take a penalty if he/she proceeds with the withdrawal\n"; writeToET(et.str()); et.str("");
		warning:
			// Warn the user if they want to proceed with the withdrawal.
			cout << "Because this transaction will bring your chequing account's balance below $1000.00, a\n";
			cout << "$2.00 fee will applied. Do you wish to continue?\n";
			cout << "1: Yes\n";
			cout << "2: No\n";
			switch (getNumber()) {
			case 1:
			{
				et << theCustomer.getId() << " selected 1: Proceed with penalty\n"; writeToET(et.str()); et.str("");
				// Go through with the transaction.
				// Check if the $2.00 penalty will put the balance below $0.
				if (theCustomer.getChequing() - x - 2.00 < 0) {
					et << theCustomer.getId() << " could not complete the withdrawal with the penalty\n"; writeToET(et.str()); et.str("");
					cout << endl << "Error: Insufficient funds. You have $" << fixed << theCustomer.getChequing() << " in your chequing account.\n";
					goto start;
				}
				else {
					// Perform the transaction.
					theCustomer.setChequing(theCustomer.getChequing() - x - 2.00);
					cout << "Successfully withdrew $" << fixed << x << " from chequing account.\n";
					cout << "Your current chequing balance is $" << fixed << theCustomer.getChequing();
					et << theCustomer.getId() << " completed the withdrawal with a penalty\n"; writeToET(et.str()); et.str("");
					return;
				}
				break;
			}
			case 2:
			{
				et << theCustomer.getId() << " selected 2: Cancel\n"; writeToET(et.str()); et.str("");
				// Cancel the transaction and exit the withdraw menu.
				cout << "Withdrawal canceled. You have not been charged.\n";
				return;
			}
			default:
			{
				et << theCustomer.getId() << " entered invalid input\n"; writeToET(et.str()); et.str("");
				cout << endl << "Error: Invalid input\n";
				goto warning;
			}

			}
		}
		else { // No penalty.
			theCustomer.setChequing(theCustomer.getChequing() - x);
			cout << endl << "Successfully withdrew $" << fixed << x << " from chequing account\n";
			cout << "Your current chequing balance is $" << fixed << theCustomer.getChequing();
			et << theCustomer.getId() << " successfully withdrew money without a penalty\n"; writeToET(et.str()); et.str("");
		}

	}
}

void withdrawSavings(Customer& theCustomer) {
start:
	cout << endl << "How much would you like to withdraw?\n";
	cout << "Enter 0 to cancel.\n";
	double x = getDouble();
	if (x == 0) {
		et << theCustomer.getId() << " entered 0 to cancel\n"; writeToET(et.str()); et.str("");
		return;
	}
	else if (x > theCustomer.getSavings()) {
		et << theCustomer.getId() << " tried to withdraw too much money\n"; writeToET(et.str()); et.str("");
		cout << endl << "Error: Insufficient funds. You have $" << fixed << theCustomer.getSavings() << " in your savings account.\n";
		goto start;
	}
	else { // Perform the transaction.
		theCustomer.setSavings(theCustomer.getSavings() - x);
		cout << "Successfully withdrew $" << fixed << x << " from savings account\n";
		cout << "Your current savings balance is $" << fixed << theCustomer.getSavings();
		et << theCustomer.getId() << " successfully withdrew money from his/her savings\n"; writeToET(et.str()); et.str("");
	}
}


void saveCustomers(vector<Customer>& Customers) {
	// Sort the Customers vector by account number.
	sortByAccountNumber(Customers);
	ofstream outputFile("Customers.txt");
	for (int i = 0; i < Customers.size(); i++) {
		outputFile << Customers[i].getId() << " " << fixed << Customers[i].getChequing() << " " << fixed << Customers[i].getSavings() << " " << Customers[i].getFirstName();
		outputFile << " " << Customers[i].getLastName() << " " << Customers[i].getPin() << " ";
		outputFile << fixed << Customers[i].getCredLimit() << endl;
	}
	outputFile.close();
}

void saveExternals(Manager manager, Maitenance maintain) {

	ofstream outputFile("externals.txt");

	outputFile << "MaintenancePerson " << maintain.getID() << endl;
	outputFile << "Manager " << manager.getID() << endl;
	outputFile << "EndOfMonth " << endOfMonth;
	outputFile.close();
}



void printCustomerMenu(Customer& theCustomer, bool cheq, bool sav) {
	cout << endl << "****************************************************** \n";	
	cout << endl << "MENU \n"; writeToET(et.str()); et.str("");
	if (!cheq) {
		cout << "1: OPEN CHEQUING ACCOUNT\n";
	}
	else
		cout << "1: CLOSE CHEQUING ACCOUNT\n";

	if (!sav) {
		cout << "2: OPEN SAVINGS ACCOUNT\n";
	}
	else
		cout << "2: CLOSE SAVINGS ACCOUNT\n";

	cout << "3: DEPOSIT\n";
	cout << "4: WITHDRAW\n";
	cout << "5: VIEW BALANCE\n";
	cout << "6: TRANSFER\n";
	cout << "7: LOGOUT\n" << endl;
	cout << "\n\n\tSelect Your Option (1-7) ";
}
void printManMenu() {
	cout << endl << "****************************************************** \n";
	cout << endl << "MENU\n"; writeToET(et.str()); et.str("");
	cout << "1: CREATE AN ACCOUNT\n";
	cout << "2: DELETE AN ACCOUNT\n";
	cout << "3: DISPLAY AN ACCOUNT'S DETAILS\n";
	cout << "4: DISPLAY ALL ACCOUNTS\n";
	cout << "5: LOGOUT\n";
	cout << "\n\n\tSelect Your Option (1-5) ";
}
void managerMenu(vector<Customer>& Customers) {
	cout << endl << "Welcome Manager!\n";
start:
	bool logout = false;
	while (!logout) {

		printManMenu();

		switch (getNumber()) {

			// Create an account.   
		case 1:
		{
			et << "Manager selected 1: Create an account\n"; writeToET(et.str()); et.str("");
			// Automatically generate a new account number.
			int newAccNumber;
			if (Customers.empty()) {
				et << "First user account #100 created\n"; writeToET(et.str()); et.str("");
				newAccNumber = 100;
			}
			else { // Generate a new account number by taking the last number on the on the Customers vector and adding 1 to it.
				et << "Generating a new Customer account number\n"; writeToET(et.str()); et.str("");
				newAccNumber = Customers[Customers.size() - 1].getId() + 1;
			}

			// Get the first name of the new Customer.
			bool valid1 = false;
			valid1 = false;
			string firstName;
			while (!valid1) {
				cout << "Please enter the first name of the new Customer.\n";
				cout << "To cancel, enter 0.\n";
				cin >> firstName;
				if (firstName.compare("0") == 0) { // If the first name is "0" then...
					et << "Manager canceled\n"; writeToET(et.str()); et.str("");
					// Escape back to the manager menu.
					goto start;
				}
				else if (firstName.size() >= 1) {
					et << "Manager entered a valid name\n"; writeToET(et.str()); et.str("");
					valid1 = true;
				}
				else {
					et << "Manager entered an invalid name \n"; writeToET(et.str()); et.str("");
					cout << "Please enter a valid name.\n";
				}
			}

			// Get the last name of the new Customer.
			valid1 = false;
			string lastName;
			while (!valid1) {
				cout << "Please enter the last name of the new Customer.\n";
				cout << "To cancel, enter 0.\n";
				cin >> lastName;
				if (lastName.compare("0") == 0) { // If the last name is "0" then...
					et << "Manager canceled\n"; writeToET(et.str()); et.str("");
					// Escape back to the manager menu.
					goto start;
				}
				else if (lastName.size() >= 1) {
					et << "Manager entered a valid name\n"; writeToET(et.str()); et.str("");
					valid1 = true;
				}
				else {
					et << "Manager entered an invalid name\n"; writeToET(et.str()); et.str("");
					cout << "Please enter a valid name.\n";
				}
			}

			// Attempt to open the account.
			bool success = createAccount(Customers, newAccNumber, firstName, lastName);
			if (!success) {
				et << "Manager successfully opened an account\n"; writeToET(et.str()); et.str("");
				cout << "Could not open account: account number already exists.\n";
			}
			else {
				et << "Manager could not open the account\n"; writeToET(et.str()); et.str("");
				cout << "Account #" << newAccNumber << " for user " << firstName << " " << lastName << " was successfully created.\n";
			}
			break;
		}

		// Delete an account.   
		case 2:
		{
			et << "Manager selected 2: Delete account\n"; writeToET(et.str()); et.str("");
			bool valid2 = false;
			// Get the account number.
			while (!valid2) {
				cout << "Please enter an account number to delete.\n";
				cout << "To cancel, enter 0.\n";
				int accNumber = getNumber();
				if (accNumber == 0) {
					et << "Manager canceled\n"; writeToET(et.str()); et.str("");
					goto start;   //Escape back to the manager menu.  
				}
				// Do not allow the account to be deleted if there is still money in it
				int x = getAccountIndex(Customers, accNumber, 0, Customers.size() - 1);
				if (x == -1) {
					cout << "Account #" << accNumber << " could not be found.\n";
				}
				else if (Customers[x].getSavings() != 0 || Customers[x].getChequing() != 0) {
					et << "Manager tried to delete an account with a balance on it\n"; writeToET(et.str()); et.str("");
					cout << "Account #" << accNumber << " could not be deleted since there exists a balance on their savings and/or chequing account.\n";
				}
				// Otherwise, attempt to delete the account.
				else {
					et << "Manager is asked for confirmation of the deletion\n"; writeToET(et.str()); et.str("");
					// Ask for confirmation.
					cout << "Are you sure you want to delete account #" << accNumber << " owned by " << Customers[x].getLastName() << ", " << Customers[x].getFirstName() << "?\n";
					cout << "1: Yes\n";
					cout << "2: No\n";

					switch (getNumber()) {
					case 1: { // Attempt to delete.
						et << "Manager selected 1: Proceed\n"; writeToET(et.str()); et.str("");
						bool success = deleteAccount(Customers, accNumber);
						if (!success) {
							cout << "Account #" << accNumber << " could not be deleted.\n";
						}
						else {
							cout << "Account #" << accNumber << " successfully removed.\n";
							valid2 = true;
							break;
						}
					}
					case 2: { // Go back to the main menu.
						et << "Manager selected 2: Cancel\n"; writeToET(et.str()); et.str("");
						cout << "Deletion canceled.\n";
						goto start;
					}
					default:
					{
						et << "Manager entered invalid input\n"; writeToET(et.str()); et.str("");
						cout << "Error: Invalid input\n";
					}

					}

				}
			}
			break;
		}

		// Display an account.    
		case 3:
		{
			et << "Manager selected 3: Display an account\n"; writeToET(et.str()); et.str("");
			bool valid3 = false;
			while (!valid3) {
				if (Customers.size() == 0) {
					cout << "No accounts to display.\n";
					goto start;
				}
				cout << "Please enter an account to view.\n";
				cout << "To cancel, enter 0.\n";
				int accNumber = getNumber();
				if (accNumber == 0) goto start;
				// Check if the account is in the database.
				if (existsInDatabase(Customers, accNumber)) {
					// Find the location at which the account is stored in the vector
					int i = getAccountIndex(Customers, accNumber, 0, Customers.size() - 1);
					// Display the data.
					cout << "********************************************" << endl;
					cout << "Account Number:      " << Customers[i].getId() << endl;
					cout << "Name:                " << Customers[i].getLastName() << ", " << Customers[i].getFirstName() << endl;
					cout << "Savings Account:     $" << fixed << Customers[i].getSavings() << endl;
					cout << "Chequing Account:    $" << fixed << Customers[i].getChequing() << endl;
					cout << "********************************************" << endl;
					valid3 = true;
					et << "Manager successfully displayed an account\n"; writeToET(et.str()); et.str("");
				}
				else {
					et << "Manager entered an invalid account number\n"; writeToET(et.str()); et.str("");
					cout << "Account " << accNumber << " could not be found.\n";
				}
			}
			break;
		}

		// Display all accounts.    
		case 4:
		{
			et << "Manager selected 4: Display all accounts\n"; writeToET(et.str()); et.str("");
			if (Customers.size() == 0) {
				cout << "No accounts to display.\n";
			}
			else {
				// Display all accounts by account number.
				for (int i = 0; i < Customers.size(); i++) {
					// Display the data.
					cout << "********************************************" << endl;
					cout << "Account Number:       " << Customers[i].getId() << endl;
					cout << "Name:                 " << Customers[i].getLastName() << ", " << Customers[i].getFirstName() << endl;
					cout << "Savings Account:      $" << fixed << Customers[i].getSavings() << endl;
					cout << "Chequing Account:     $" << fixed << Customers[i].getChequing() << endl;
				}
				cout << "********************************************" << endl;
			}
			break;
		}
		// logout      
		case 5:
		{cout << "\n\n\tThanks for using bank managemnt system\n";
		et << "Manager selected 6: Logout\n"; writeToET(et.str()); et.str("");
		intro();
		return;
			break;
		}
		default:
			et << "Manager entered invalid input\n"; writeToET(et.str()); et.str("");
			cout << "Error: Invalid input\n";

		}
	}
}
bool createAccount(vector<Customer>& Customers, int accID, string firstName, string lastName)
{
	et << "createAccount(vector<Customer>& Customers, int accID = " << accID << "string firstName = "
		<< firstName << "string lastName = " << lastName << ")\n"; writeToET(et.str()); et.str("");
	// Check if the account ID already exists in the database.
	if (existsInDatabase(Customers, accID)) {
		et << "The account ID already exists in the database\n"; writeToET(et.str()); et.str("");
		return false;
	}
	else {
		// Create a new Customer object.
		Customer newCustomer = Customer();
		newCustomer.setFirstName(firstName);
		newCustomer.setLastName(lastName);
		newCustomer.setId(accID);
		// Add to the vector.
		Customers.push_back(newCustomer);
		// Add to the database.
		saveCustomers(Customers);
		et << "Manager successfully opened an account.\n"; writeToET(et.str()); et.str("");
		return true;
	}
}


bool deleteAccount(vector<Customer>& Customers, int accID)
{
	// Check if the vector is empty.
	if (Customers.empty()) {
		return false;
	}
	// Check if the account ID exists in the database.
	else if (existsInDatabase(Customers, accID)) {
		// Perform a binary search method to get the index value of the account.
		int i = getAccountIndex(Customers, accID, 0, Customers.size() - 1);
		// Remove the account from the vector.
		Customers.erase(Customers.begin() + i);
		// Update the database.
		saveCustomers(Customers);
		et << "Manager successfully deleted an account\n"; writeToET(et.str()); et.str("");
		return true;
	}
	else {
		et << "Manager failed to delete an account\n"; writeToET(et.str()); et.str("");
		return false;
	}

}

void sortByAccountNumber(vector<Customer>& Customers)//sorts accounts by number. this method is mainly used whrn manager wants to display everything
{
	if (Customers.empty()) {
		return;
	}
	// Sort by account number.
	std::sort(Customers.begin(), Customers.end(), compareAccountNums);

}

bool existsInDatabase(vector<Customer>& Customers, int targetAccID)
{
	if (Customers.empty()) {
		return false;
	}
	// First ensure that the vector is ordered (by account number).
	sortByAccountNumber(Customers);
	// Search using the binary search method.
	int result = getAccountIndex(Customers, targetAccID, 0, Customers.size() - 1);
	if (result == -1) {
		return false;
	}
	else {
		return true;
	}

}

bool compareAccountLastName(Customer a, Customer b) {
	return (a.getLastName() < b.getLastName());
}

bool compareAccountNums(Customer a, Customer b) {
	return (a.getId() < b.getId());
}
int getAccountIndex(vector<Customer>& Customers, int targetAccNum, int low, int high) {
	et << "int getAccountIndex(vector<Customer>& Customers, int targetAccNum = " << targetAccNum << " int low = "
		<< low << "int high = " << high << "\n"; writeToET(et.str()); et.str("");
	// Perform a binary search for the account.
	// This assumes we have an ordered list.
	if (high < low) return -1; // Not found.
	int mid = low + ((high - low) / 2);
	if (Customers[mid].getId() > targetAccNum) {
		return getAccountIndex(Customers, targetAccNum, low, mid - 1);
	}
	else if (Customers[mid].getId() < targetAccNum) {
		return getAccountIndex(Customers, targetAccNum, mid + 1, high);
	}
	else {
		et << "int getAccountIndex() found " << mid << "\n"; writeToET(et.str()); et.str("");
		return mid; // Found.
	}
}

void maintenanceMenu(void) {
	et << "maintenanceMenu()\n"; writeToET(et.str()); et.str("");
	cout << endl << "Welcome Maintenaner!\n";
start:
	bool logout = false;
	while (!logout) {
		cout << endl << "***********---What would you like to do?---*********** \n";
		cout << "1: TURN EXECUTION TRACE ON/OFF\n";
		cout << "2: PRINT EXECUTION TRACE\n";
		cout << "3: CLEAR EXECUTION TRACE LOG\n";
		cout << "4: LOGOUT\n";
		cout << "\n\n\tSelect Your Option (1-4) ";
		switch (getNumber()) {

			// Toggle execution tracing.
		case 1: {
			et << "Maintenance selected 1: Toggle execution tracing\n"; writeToET(et.str()); et.str("");
			exTraceSwitch();
			break;
		}

				// Print execution traces.
		case 2: {
			et << "Maintenance selected 2: print Execution traces\n"; writeToET(et.str()); et.str("");
			printExecutionTraces();
			break;
		}

		case 3: {
			et << "Maintenance selected 3: Clear execution traces log\n"; writeToET(et.str()); et.str("");
			// Ask for confirmation.
			cout << "Are you sure you want to clear the execution traces log?\n";
			cout << "1: Yes\n";
			cout << "2: No\n";
			et << "Maintenance is asked for confirmation on the log clear\n"; writeToET(et.str()); et.str("");
			switch (getNumber()) {
			case 1: {
				et << "Maintenance selected 1: Proceed\n"; writeToET(et.str()); et.str("");
				clearExecutionTracesLog();
				goto start;
			}

			case 2: {
				et << "Maintenance selected 2: Cancel\n"; writeToET(et.str()); et.str("");
				goto start;
			}
			default: {
				et << "Maintenance entered invalid input\n"; writeToET(et.str()); et.str("");
				cout << "Invalid input\n";
			}
			}
			break;
		}

				// Logout.
		case 4: {
			cout << "\n\n\tThanks for using bank managemnt system\n";
			et << "Maintenance selected 4: Logout\n"; writeToET(et.str()); et.str("");
			intro();
			return; // Exit the maintenance menu.
		}

		default: {
			et << "Maintenance entered invalid input\n"; writeToET(et.str()); et.str("");
			cout << "Invalid input\n";
		}
		}
	}
}
//  Call this function to prompt the user for an int value. Returns that value
int getNumber() {
	et << "User is entering a number\n"; writeToET(et.str()); et.str("");
	int amount;
	cin >> amount;
	//If the value endered was not of type int
	while (cin.fail()) {
		cin.clear();
		cin.ignore(numeric_limits<streamsize>::max(), '\n');
		cout << endl << "Invalid input.  Enter a NUMBER: \n"; writeToET(et.str()); et.str("");
		et << "User entered an invalid number\n"; writeToET(et.str()); et.str("");
		cin >> amount;
	}
	et << "User correctly entered the number" << amount << "\n"; writeToET(et.str()); et.str("");
	return amount;
}
// Call this function to prompt the user for a double value. Returns that value
double getDouble() {
	et << "User is entering a double\n"; writeToET(et.str()); et.str("");
	double amount;
	cin >> amount;
	//If the value entered was not of type double
	while (cin.fail()) {
		cin.clear();
		cin.ignore(numeric_limits<streamsize>::max(), '\n');
		cout << endl << "Invalid input.  Enter a NUMBER: \n";
		et << "User entered an invalid double \n"; writeToET(et.str()); et.str("");
		cin >> amount;
	}
	et << "User correctly entered a double\n"; writeToET(et.str()); et.str("");
	return amount;
}

bool penalty(Customer& theCustomer, double amount) {
	et << "penalty(Customer& theCustomer #" << theCustomer.getId() << " double amount = " << amount << ")\n"; writeToET(et.str()); et.str("");
	// Check if the transaction will bring the Customer's chequing account down below $1000.
	if (theCustomer.getChequing() - amount < 1000.00) {
		et << "There is a penalty"; writeToET(et.str()); et.str("");
		return true;
	}
	else {
		et << "There is no penalty\n"; writeToET(et.str()); et.str("");
		return false;
	}
}


void exTraceSwitch() {
	if (executionTraces == true) {
		cout << "Execution tracing is ON. Do you want to switch if OFF?\n";
		cout << "1: Yes\n";
		cout << "2: No\n";
		switch (getNumber()) {

		case 1: {    //Yes, switch OFF
			string line;
			executionTraces = false;
			// Change the first line of the file.
			ifstream etFile;
			etFile.open("execution_traces.txt");
			if (etFile.is_open()) {
				// We need to make a temporary file to save our exec traces.
				ofstream tempFile;
				tempFile.open("temp.txt");
				if (tempFile.is_open()) {
					// Start copying the contents of execution_traces.txt into temp.txt
					// Skip the first line.
					getline(etFile, line);
					while (getline(etFile, line)) {
						tempFile << line << endl;
					}
					tempFile.close();
					etFile.close();
					// Overwrite execution_traces.txt.
					ofstream etFileWrite("execution_traces.txt");
					etFileWrite << "0\n"; // The file now only contains "0".
										  // Add the rest of the file from the temp.
					ifstream tempFileRead("temp.txt");
					while (getline(tempFileRead, line)) {
						etFileWrite << line << endl;
					}
					etFileWrite.close();
					tempFileRead.close();
					// Remove temp.txt since we no longer need it.
					if (remove("temp.txt") != 0) cout << "Error deleting file temp.txt\n";
				}
				else {
					cout << "Could not create temp.txt\n";
					return;
				}

			}
			else {
				cout << "Could not open file execution_traces.txt\n";
				return;
			}
			cout << "Execution tracing is OFF.\n";
			break;
		}

		case 2: {    //No, keep ON
			cout << "Execution tracing is ON.\n";
			break;
		}
		default: {
			cout << "Invalid input\n";
		}
		}
	}
	else {
		cout << "Execution tracing is OFF. Do you want to switch it ON?\n";
		cout << "1: Yes\n";
		cout << "2: No\n";
		switch (getNumber()) {

		case 1: {    //Yes, switch ON
			string line;
			executionTraces = true;
			// Change the first line of the file.
			ifstream etFile;
			etFile.open("execution_traces.txt");
			if (etFile.is_open()) {
				// We need to make a temporary file to save our exec traces.
				ofstream tempFile;
				tempFile.open("temp.txt");
				if (tempFile.is_open()) {
					// Start copying the contents of execution_traces.txt into temp.txt
					// Skip the first line.
					getline(etFile, line);
					while (getline(etFile, line)) {
						tempFile << line << endl;
					}
					tempFile.close();
					etFile.close();
					// Overwrite execution_traces.txt.
					ofstream etFileWrite("execution_traces.txt");
					etFileWrite << "1\n"; // The file now only contains "1".
										  // Add the rest of the file from the temp.
					ifstream tempFileRead("temp.txt");
					while (getline(tempFileRead, line)) {
						etFileWrite << line << endl;
					}
					etFileWrite.close();
					tempFileRead.close();
					// Remove temp.txt since we no longer need it.
					if (remove("temp.txt") != 0) cout << "Error deleting file temp.txt\n";
				}
				else {
					cout << "Could not create temp.txt\n";
					return;
				}

			}
			else {
				cout << "Could not open file execution_traces.txt\n";
				return;
			}
			cout << "Execution tracing is ON.\n";
			break;
		}

		case 2: {    //No, keep OFF
			cout << "Execution tracing is OFF.\n";
			break;
		}
		default: {
			cout << "Invalid input\n";
		}
		}
	}
}

void writeToET(string str) {				//method for writing the execution trace to a text file
	// Only write if execution tracing is on.
	if (executionTraces == true) {
		ofstream etFile;
		// Open the file in append mode.
		etFile.open("execution_traces.txt", ios::app);
		if (etFile.is_open()) {
			etFile << str << endl;//write
			etFile.close();//close
		}
		else {
			cout << "Could not open file execution_traces.txt\n";
		}
	}
	else {
		return;
	}
}

void printExecutionTraces() {
	et << "Maintenance is printing the execution traces\n"; writeToET(et.str()); et.str("");
	// Open execution_traces.txt and print the file (excluding the first line).
	string line;
	ifstream etFile;
	etFile.open("execution_traces.txt");
	if (etFile.is_open()) {
		// Skip the first line.
		getline(etFile, line);
		while (getline(etFile, line))
		{
			cout << line << endl;
		}
		etFile.close();//close
	}
	else {
		cout << "Could not open file execution_traces.txt\n";
	}

}

void clearExecutionTracesLog() {
	et << "Maintenance clearing the execution log\n"; writeToET(et.str()); et.str("");
	// Clear everything in execution_traces.txt EXCEPT for the first line which governs the state of exec tracing.
	// Do this by overwriting the file with the current state of executionTraces.
	ofstream etFile;
	etFile.open("execution_traces.txt");
	if (etFile.is_open()) {
		if (executionTraces == true) {
			etFile << "1\n";
		}
		else {
			etFile << "0\n";
		}
		etFile.close();
	}
	else {
		cout << "Could not open file execution_traces.txt\n";
	}


}

void press_enter() {//i know this method is useless. i was gonna do more with it but i forgot
	cout << "\n\n\n\t  Press the Enter button to continue";
}

void intro()
{
	cool_ascii();//calls the function to display my SUPER COOL ascii art
	cout << "\n\n\n\t  LAM BANK MANAGEMENT SYSTEM";
	cout << "\n\n\t            By : Vivian Lam";
	cout << "\n\n\n\t  Welcome!";
	press_enter();
	cin.get();
}

void cool_ascii() {
	cout << "  ......................................................\n";
	cout << "  ../$$..../$$......../$$...............................\n";
	cout << "  .|.$$...|.$$|......|.$$...............................\n";
	cout << "  .|.$$...|.$$.......|.$$......../$$$$$$../$$$$$$/$$$$..\n";
	cout << "  .|..$$./.$$/|......|.$$.......|____..$$|.$$_..$$_..$$.\n";
	cout << "  ..\\..$$.$$/........|.$$......../$$$$$$$|.$$.\\.$$.\\.$$.\n";
	cout << "  ...\\..$$$/.........|.$$......./$$__..$$|.$$.|.$$.|.$$.\n";
	cout << "  ....\\..$/..........|.$$$$$$$$|..$$$$$$$|.$$.|.$$.|.$$.\n";
	cout << "  .....\\_/...........|________/.\\_______/|__/.|__/.|__/.\n";
	cout << "  ......................................................\n";

}

//***************************************************************
//    			END OF PROJECT
//***************************************************************