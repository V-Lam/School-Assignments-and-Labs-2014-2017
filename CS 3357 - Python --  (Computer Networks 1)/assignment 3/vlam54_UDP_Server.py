import binascii
import socket
import struct
import sys
import hashlib

UDP_IP = "127.0.0.1"
UDP_PORT_server = 5006#different ports because for assignment 4 we want same loop back address for ip
UDP_PORT_client = 5005
unpacker = struct.Struct('I I 8s 32s')


#Create the socket and listen
sock = socket.socket(socket.AF_INET, # Internet
                     socket.SOCK_DGRAM) # UDP
sock.bind((UDP_IP, UDP_PORT_server))


#function for creating the SERVER checksum - MD5 Checksum of packet (32 Bytes)
def create_server_checksum(ACK, SEQ):
    values = (ACK,SEQ)
    UDP_Data = struct.Struct('I I')# I I cus 1 bit each for ACK and SEQ
    packed_data = UDP_Data.pack(*values)
    chksum =  bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8") #utf 8 encoding
    #return the checksum
    return chksum;


#function for building the UDP packet:
#ACK - Indicates if packet is ACK or not. Valid values (1 or 0)
#SEQ - Sequence number of packet. Valid values (1 or 0)
#SENDER DOES NOT SEND BACK DATA
#CHKSUM - MD5 Checksum of packet (32 Bytes)
def build_UDP_server_packet(ACK, SEQ, chksum):
    values = (ACK,SEQ,chksum) #ACK, SEQ, CHECKSUM
    UDP_Packet_Data = struct.Struct('I I 32s')#ACK, SEQ, CHECKSUM
    UDP_Packet = UDP_Packet_Data.pack(*values)
    #return the packet ready to be sent
    return UDP_Packet;


#Send the UDP Packet
def send_packet(UDP_Packet):
    sock.sendto(UDP_Packet, (UDP_IP, UDP_PORT))

#----------------------------------------------------------------------------


#always listening
while True:
    #Receive Data
    data, addr = sock.recvfrom(1024) #buffer size is 1024 bytes
    UDP_Packet = unpacker.unpack(data)
    print("received from:", addr)
    print("received message:", UDP_Packet)
    #Create the Checksum for comparison
    values = (UDP_Packet[0],UDP_Packet[1],UDP_Packet[2]) #ACK SEQ DATA. UDP_Packet[3] is the checksum
    packer = struct.Struct('I I 8s')
    packed_data = packer.pack(*values)
    chksum =  bytes(hashlib.md5(packed_data).hexdigest(), encoding="UTF-8")
    #Compare Checksums to test for corrupt data
    if UDP_Packet[3] == chksum:
        print('CheckSums Match, Packet OK')

    else:
        print('Checksums Do Not Match, Packet Corrupt')
        #corrupt

    #now we send back a response
    serverACK=1
    currentSEQ= UDP_Packet[1]
    #create checksum
    currentchecksum = create_server_checksum(serverACK, currentSEQ)
    #build packet
    UDP_Packet = build_UDP_server_packet(serverACK, currentSEQ, currentchecksum)
    #send the packet
    print("about to send")
    sock.sendto(UDP_Packet, (UDP_IP, UDP_PORT_client))
    print("sent")
    print("\n")