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
class Maitenance
{
public:
	Maitenance(int ID);
	Maitenance();
	~Maitenance();
	int getID();
	void setID(int newID);
private:
	int id;
};