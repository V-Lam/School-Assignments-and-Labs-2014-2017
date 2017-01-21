#pragma once
#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>
#include <limits>
#include <algorithm>
#include <stdio.h>
using namespace std;
class Customer
{
public:
	Customer(); //Constructor
	~Customer() {} //Destructor
	//Setters
	void setChequing(double chequing);
	void setSavings(double saving);
	void setId(int cid);
	void setFirstName(string name);
	void setLastName(string name);
	void setCredLimit(double credL);
	void setPin(int PIN);
	//Getters
	double getSavings();		// return amount under savings account
	double getChequing();	// Return amount under chequing account
	int getId();					// Return user Id;
	string getFirstName();			// Return first name;
	string getLastName();			// Return last name;
	double getCredLimit();			
	int getPin();
private:
	int id, pin;
	double chequingAcc, savingsAcc, cred, limit;
	string name1, name2;
};