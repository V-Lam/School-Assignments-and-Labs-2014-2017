#BEFORE RUNNING PLEASE CHANGE THE IP ADDRESS
#PLEASE USE PYTHON 2.7 TO RUN

#Vivian Lam
#CS 3357
#OCT 21 2016

#from socket import *
import socket
import datetime
import time

#Variable intialization
#TCP_IP = '192.168.10.130'
#TCP_IP = '172.30.70.129'
TCP_IP = '192.168.10.1'
TCP_PORT = 5005
acceptedString = "What is the current date and time?"
rejectMessage = "Invalid query, try again"

#server socket always listening for client
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))

#keeps listening
while 1:
	s.listen(1)

	#creates the new socket to send info
	conn, addr = s.accept()
	print('Server Address:', TCP_IP)
	print('Client Address:', addr)
	print("Connection to Client Established")
	
	#waits to receive info from client
	print ('\n')
	print ('Awaiting client query...')
	print ('\n')

	#receive the response
	requestString = conn.recv(1024)#1024 is the buffer size

	#if request is valid:  What is the current date and time?
	if requestString == acceptedString:
		#respond to request: "Current Date and Time - MM/DD/YYYY hh:mm:ss"
		now = datetime.datetime.now()
		month = now.month
		day = now.day
		year = now.year
		hour = now.hour
		minute = now.minute
		second = now.second

		#answer = "Current Date and Time - %m/%d/%y %H:%M:%S", month, day, year, hour, minute, second
		#conn.send(answer.encode())
		#conn.send(answer)

		answer = "Current Date and Time - " + time.strftime("%m/%d/%Y") + " " + time.strftime("%H:%M:%S")
		conn.send(answer)

	#else send the reject message
	else:
		#conn.send(rejectMessage.encode())
		conn.send(rejectMessage)
	#success

	#close connection socket
	conn.close()

#but keep listening!
