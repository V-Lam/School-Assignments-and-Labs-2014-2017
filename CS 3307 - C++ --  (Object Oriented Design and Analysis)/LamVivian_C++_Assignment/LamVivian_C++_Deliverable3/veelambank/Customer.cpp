#include "Customer.h"
#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>
#include <limits>
#include <algorithm>
#include <stdio.h>
using namespace std;
Customer::Customer()
{
	id = 0;
	chequingAcc = 0.0;
	savingsAcc = 0.0;
	name1 = " ";
	name2 = " ";
	limit = 0.0;
	cred = 0.0;
	pin = 0;
	
}
//Setters
void Customer::setChequing(double chequing) { chequingAcc = chequing; }
void Customer::setSavings(double saving) { savingsAcc = saving; }
void Customer::setId(int cid) { id = cid; }
void Customer::setFirstName(string name) { name1 = name; }
void Customer::setLastName(string name) { name2 = name; }
void Customer::setCredLimit(double credL) { limit = credL; }
void Customer::setPin(int PIN) { pin = PIN; }
//Getters
double Customer::getSavings() { return savingsAcc; }		// return amount under savings account
double Customer::getChequing() { return chequingAcc; }		// Return amount under chequing account
int Customer::getId() { return id; }						// Return user Id;
string Customer::getFirstName() { return name1; }			// Return first name;
string Customer::getLastName() { return name2; }			// Return last name;
double Customer::getCredLimit() { return limit; }
int  Customer::getPin() { return pin; }
