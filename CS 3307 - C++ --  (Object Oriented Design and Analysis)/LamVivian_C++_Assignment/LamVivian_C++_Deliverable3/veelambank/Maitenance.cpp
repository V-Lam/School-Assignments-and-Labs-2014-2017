#include "Maitenance.h"
#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>
#include <limits>
#include <algorithm>
#include <stdio.h>
using namespace std;
Maitenance::Maitenance(int ID)
{
	id = ID;
}

Maitenance::Maitenance() {
	id = NULL;
}
Maitenance::~Maitenance()//deconstructor
{
}

int Maitenance::getID() {
	return id;
}
void Maitenance::setID(int newID) {
	id = newID;
}

