# Echo client program
import socket
import time
HOST = '115.29.4.152'    # The remote host
PORT = 1883              # The same port as used by the server
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

a = '\x10.\x00\x04MQTT\x04\xc2\x00\x1e\x00\x0can3158786236\x00\nsmalKettle\x00\x08smal2014'

b = '\x82\x17\x00\x01\x00\x12/n/A1/an3158786236\x00'

c = '\x82\'\x00\x02\x00\"/p/D2/201502001855/A1/an3158786236\x00'

d = '\x82\x17\x00\x03\x00\x12/b/D2/201502001855\x00'

e = '0#\x00\x12/q/D2/201502001855\x01A1an3158786236'

# open
f = '0>\x00\x12/q/D2/2015020018557A1an3158786236\x0b\x00\x00\x00\x00\x00\x00\x01\x02\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00d\x00\x00\x00\x00\x00\x00'

# keep
h = '0>\x00\x12/q/D2/2015020018557A1an3158786236\x0c\x00\x00\x00\x00\x00\x00\x00\x02\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x012\xd0\x02\x00\x00\x00\x00'

# close
i = '0#\x00\x12/q/D2/2015020018558A1an3158786236'

# process
j = '0>\x00\x12/q/D2/2015020018557A1an3158786236\r\x00\x00\x00\x00\x00\x00\x01\x02\x03\x03\x00\x00\x00\x00\x00\x00\x00\x00\x00d\x00\x00\x00\x00\x00\x00'

s.connect((HOST, PORT))
s.sendall(a)
data = s.recv(1024)
print data.encode('hex')


s.sendall(b)
data = s.recv(1024)
print data.encode('hex')

s.sendall(c)
data = s.recv(1024)
print data.encode('hex')

s.sendall(d)
data = s.recv(1024)
print data.encode('hex')

s.sendall(e)
data = s.recv(1024)
print data

s.sendall(j)
data = s.recv(1024)
print data.encode('hex')

# time.sleep(2)
# s.sendall(h)
# data = s.recv(1024)
# print data.encode('hex')
# time.sleep(0.1)
# s.sendall(e)
# data = s.recv(1024)
# print data.encode('hex')

# time.sleep(0.1)
# s.sendall(f)
# data = s.recv(1024)
# print data.encode('hex')

# time.sleep(0.1)
# time.sleep(100)

