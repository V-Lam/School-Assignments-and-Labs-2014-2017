#BEFORE RUNNING PLEASE CHANGE THE IP ADDRESS
#PLEASE USE PYTHON 2.7 TO RUN

#Vivian Lam
#CS 3357
#OCT 21 2016

#from socket import *
import socket

#Variable intialization
#TCP_IP = '192.168.10.130'
#TCP_IP = '172.30.70.129'
TCP_IP = '192.168.10.1'
TCP_PORT = 5005
#rejectMessage = "Invalid query, try again"

#connect to the server socket
print ("Attempting to contact server at ",TCP_IP,":",TCP_PORT)
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))
print ("Connection to Server Established")



#Prompt user
print ('\n')
print ('Valid questions (case sensitive and include punctuation):')
print ('What is the current date and time?')
print ('\n')


#prompt and recieve input from user
inputQuery = ""
inputQuery = raw_input('Please ask a valid question:')

#send the input to the server
s.send(inputQuery)

#receive the response
answer = s.recv(1024) #1024 is the buffer size



#success: response received from server. print it
print 'From Server: ', answer

#close connection
s.close()
