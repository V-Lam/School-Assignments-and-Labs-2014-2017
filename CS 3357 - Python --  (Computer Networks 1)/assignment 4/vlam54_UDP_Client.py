import binascii
import socket
import struct
import sys
import hashlib

UDP_IP = "127.0.0.1"
UDP_PORT_client = 5005#different ports because for assignment 4 we want same loop back address for ip
UDP_PORT_server=5006
unpacker = struct.Struct('I I 32s')#struct for receiving the packet form server. ACK, SEQ, Checksum. no data!


print("UDP target IP:", UDP_IP)
print("UDP target port:", UDP_PORT_server)
print("\n")
"""
#----------------------------------------------------------------------------
#ORIGINAL CODE:

#Create the Checksum
values = (0,0,b'TestData')
UDP_Data = struct.Struct('I I 8s')
packed_data = UDP_Data.pack(*values)
chksum =  bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8")

#Build the UDP Packet
values = (0,0,b'TestData',chksum) #ACK, SEQ, DATA, CHECKSUM
UDP_Packet_Data = struct.Struct('I I 8s 32s')
UDP_Packet = UDP_Packet_Data.pack(*values)

#Send the UDP Packet
sock = socket.socket(socket.AF_INET, # Internet
                     socket.SOCK_DGRAM) # UDP
sock.sendto(UDP_Packet, (UDP_IP, UDP_PORT))
#----------------------------------------------------------------------------
"""

#function for creating the checksum - MD5 Checksum of packet (32 Bytes)
def create_client_checksum(ACK, SEQ, theData):
	#byteData = theData.encode()
	byteData = bytes(theData, encoding="UTF-8")
	values = (ACK,SEQ,byteData) #b casts the data to byte encoding
	UDP_Data = struct.Struct('I I 8s')
	packed_data = UDP_Data.pack(*values)
	chksum =  bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8") #utf 8 encoding
	#return the checksum
	return chksum;


#function for building the UDP packet:
#ACK - Indicates if packet is ACK or not. Valid values (1 or 0)
#SEQ - Sequence number of packet. Valid values (1 or 0)
#DATA - Application Data (8 bytes)
#CHKSUM - MD5 Checksum of packet (32 Bytes)
def build_UDP_client_packet(ACK, SEQ, theData, chksum):
	#byteData = theData.encode()
	byteData = bytes(theData, encoding="UTF-8")
	values = (ACK,SEQ,byteData,chksum) #ACK, SEQ, DATA, CHECKSUM
	UDP_Packet_Data = struct.Struct('I I 8s 32s')
	UDP_Packet = UDP_Packet_Data.pack(*values)
	#return the packet ready to be sent
	return UDP_Packet;


#Send the UDP Packet
def send_packet(UDP_Packet):
	#send it through the socket
	sock.sendto(UDP_Packet, (UDP_IP, UDP_PORT))


#----------------------------------------------------------------------------

#Create the socket 
sock = socket.socket(socket.AF_INET, # Internet
                     socket.SOCK_DGRAM) # UDP
sock.bind((UDP_IP, UDP_PORT_client))#bind to itself, attaching ip and port saying RECEIVE HERE

sock.settimeout(0.009)#set the timer to be 9ms


"""
send the following info:
NCC-1701
NCC-1664
NCC-1017
#in order to accomplish this the client must also receive data in form of ACKS from server 
#because we are useing rdt2.2
#client sends ACK=0
#server sends ACK=1
"""


#initializing variables that check before we can send the next packet
packet1Success = False
packet2Success = False
packet3Success = False


#data 1
clientACK=0
currentSEQ=0
currentData ="NCC-1701"
#create checksum
currentchecksum = create_client_checksum(clientACK, currentSEQ, currentData)
#build packet
UDP_Packet_client = build_UDP_client_packet(clientACK, currentSEQ, currentData, currentchecksum)

while packet1Success == False:
	#send the packet
	print("bouta send packet1")
	sock.sendto(UDP_Packet_client, (UDP_IP, UDP_PORT_server))
	print("we sent dat packet")

	#start the timer = 9ms
	try:
		#receive the UDP packet
		data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
		UDP_Packet = unpacker.unpack(data)
		print("received from:", addr)
		print("received message:", UDP_Packet)
		#Create the Checksum for comparison
		values = (UDP_Packet[0],UDP_Packet[1]) #ACK SEQ Checksum. UDP_Packet[2] is the checksum
		packer = struct.Struct('I I')
		packed_data = packer.pack(*values)
		chksum =  bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8")
		#Compare Checksums to test for corrupt data
		if UDP_Packet[2] == chksum and UDP_Packet[1]==currentSEQ: #checksums match and correct sequence
			print("checksums match and sequences match, packet ok")
			print("Packet 1 success!!\n")
			print("Onto the next one\n\n")
			packet1Success=True
		else:
			print("checksums don't match OR wrong sequence\n")
			print("RETRYING :(")

	#timeout, resend and restart timer
	except socket.timeout:
		print("Timeout, resending...\n")

#data 2
currentSEQ=1
currentData ="NCC-1664"
#create checksum
currentchecksum = create_client_checksum(clientACK, currentSEQ, currentData)
#build packet
UDP_Packet_client = build_UDP_client_packet(clientACK, currentSEQ, currentData, currentchecksum)

while packet2Success == False:
	#send the packet
	print("bouta send packet2")
	sock.sendto(UDP_Packet_client, (UDP_IP, UDP_PORT_server))
	print("we sent dat packet")

	#start the timer = 9ms
	try:
		#receive the UDP packet
		data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
		UDP_Packet = unpacker.unpack(data)
		print("received from:", addr)
		print("received message:", UDP_Packet)
		#Create the Checksum for comparison
		values = (UDP_Packet[0],UDP_Packet[1]) #ACK SEQ Checksum. UDP_Packet[2] is the checksum
		packer = struct.Struct('I I')
		packed_data = packer.pack(*values)
		chksum =  bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8")
		#Compare Checksums to test for corrupt data
		if UDP_Packet[2] == chksum and UDP_Packet[1]==currentSEQ: #checksums match and correct sequence
			print("checksums match and sequences match, packet ok")
			print("Packet 2 success!!\n")
			print("Onto the next one\n\n")
			packet2Success=True
		else:
			print("checksums don't match OR wrong sequence\n")
			print("RETRYING :(")

	#timeout, resend and restart timer
	except socket.timeout:
		print("Timeout, resending...\n")

#data 3
currentSEQ=0
currentData ="NCC-1017"
#create checksum
currentchecksum = create_client_checksum(clientACK, currentSEQ, currentData)
#build packet
UDP_Packet_client = build_UDP_client_packet(clientACK, currentSEQ, currentData, currentchecksum)

while packet3Success == False:
	#send the packet
	print("bouta send packet3")
	sock.sendto(UDP_Packet_client, (UDP_IP, UDP_PORT_server))
	print("we sent dat packet")

	#start the timer = 9ms
	try:
		#receive the UDP packet
		data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
		UDP_Packet = unpacker.unpack(data)
		print("received from:", addr)
		print("received message:", UDP_Packet)
		#Create the Checksum for comparison
		values = (UDP_Packet[0],UDP_Packet[1]) #ACK SEQ Checksum. UDP_Packet[2] is the checksum
		packer = struct.Struct('I I')
		packed_data = packer.pack(*values)
		chksum =  bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8")
		#Compare Checksums to test for corrupt data
		if UDP_Packet[2] == chksum and UDP_Packet[1]==currentSEQ: #checksums match and correct sequence
			print("checksums match and sequences match, packet ok")
			print("Packet 3 success!!\n")
			print("Onto the next one\n\n")
			packet3Success=True
		else:
			print("checksums don't match OR wrong sequence\n")
			print("RETRYING :(")

	#timeout, resend and restart timer
	except socket.timeout:
		print("Timeout, resending...\n")

print("\n")
print("We done here")



sock.close()#close the socket
