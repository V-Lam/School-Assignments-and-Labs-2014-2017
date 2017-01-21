/*

ignre this class i was gonna implement more, but i got lazy so i just have my rough ideas. i mean feel free to look if oyu want


//put below in the main.cpp whenever i want to trance


//calls the print trace function 
traceTime.printTrace();

//print out the getTime to the trace file



string Trace::getTime() {//method for getting time
time_t currentTime;
struct tm *localTime;

time(&currentTime);                   // Get the current time
localTime = localtime(&currentTime);  // Convert the current time to the local time

int Day = localTime->tm_mday;
int Month = localTime->tm_mon + 1;
int Year = localTime->tm_year + 1900;
int Hour = localTime->tm_hour;
int Min = localTime->tm_min;
int Sec = localTime->tm_sec;
string time = to_string(Day) + "/" + to_string(Month) + "/" + to_string(Year) + " at " + to_string(Hour) + ":" + to_string(Min) + ":" + to_string(Sec);
return time;
}*/