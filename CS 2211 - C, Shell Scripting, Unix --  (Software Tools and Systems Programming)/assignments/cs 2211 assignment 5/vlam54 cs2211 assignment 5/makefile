#hash is only seen by the preprocessor and not the processor
#format is target-name: target dependencies
#{-tab-}actions


# MACRO definitions
CC = gcc
CFLAG = -std=c99 -Wall


# ALL targets
all: operation


# Executable operation depends on the files operation.o operation_functions.o
operation: operation.o operation_functions.o
	$(CC) $(CFLAG) -o operation operation.o operation_functions.o


# operation.o depends on the source and header files
operation.o: operation.c operation_functions.h
	$(CC) $(CFLAG) -c operation.c


# operation_functions.o depends on the source and header files
operation_functions.o: operation_functions.c operation_functions.h
	$(CC) $(CFLAG) -c operation_functions.c


# test cases
test: operation
	operation 1.5 2 3 4
	operation 3.3 0 7.8 0
	operation 0 4 0 1
	operation 2.3 0 0 9
	operation 0 3 3.4 0
	operation 0 0 1 2
	operation 3 4 0 0

#Clean the build directory
clean:
	rm -f *.o
