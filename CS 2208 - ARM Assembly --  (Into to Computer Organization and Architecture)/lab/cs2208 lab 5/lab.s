;Q1
;	AREA Lab, CODE, READONLY
;	ENTRY
;	MOV 		r1,#2			;Put 2 in register 1
;	MOV 		r2,#3			;Put 3 in register 2
;	ADD 		r0,r1,r2		;Add r1 to r2 and store result in r0
;	END

;Q2
;	AREA Lab, CODE, READONLY
;	ENTRY
;	MOV			r3,#4			;Put 4 in register 3
;	MOV			r4,#5			;put 5 in register 4
;	END

;Q3
;	AREA Lab, CODE, READONLY
;	ENTRY
;	MOV			r0,#5			;Put 5 into register 0
;	MOV			r1,#4			;Put 4 into register 1
;	MOV			r2,#3			;Put 3 into register 2
;	MOV			r3,#2			;Put 2 into register 3
;	MOV			r4,#1			;Put 1 into register 4
;	MUL			r5,r3,r4		;Multiply values stored in register 3 and 4 and store the result into register 5
;	SUB			r5,r2,r5		;Subtract the value stored in register 2 by the value in register 5 and store the result into register 5
;	ADD			r5,r5,r1		;Add the value stored in register 1 to the value stored in register 5 and store the result into register 5
;	ADD			r5,r5,r0		;Add the value stored in register 0 to the value stored in register 5 and store the result into register 5
;	END

;Q4
;	AREA Lab, CODE, READWRITE
;	ENTRY
;	LDR			r0,A			;Put 5 into register 0
;	LDR			r1,B			;Put 4 into register 1
;	LDR			r2,C			;Put 3 into register 2
;	LDR			r3,D			;Put 2 into register 3
;	LDR			r4,E			;Put 1 into register 4
;	MUL			r5,r3,r4		;Multiply values stored in register 3 and 4 and store the result into register 5
;	SUB			r5,r2,r5		;Subtract the value stored in register 2 by the value in register 5 and store the result into register 5
;	ADD			r5,r5,r1		;Add the value stored in register 1 to the value stored in register 5 and store the result into register 5
;	ADD			r5,r5,r0		;Add the value stored in register 0 to the value stored in register 5 and store the result into register 5
;	STR			r0,Z			;Store the value in register 0 into memory location Z
;A	DCD		4				;Put 4 in memory location A
;B	DCD			12				;Put 12 in memory location B
;C	DCD			-2				;Put -2 in memory location C
;D	DCD			-5				;Put -5 in memory location D
;E	DCD			-9				;Put -9 in memory location E
;Z	DCD			0				;Put 0 in memory location Z
;	END
		
;Q5
;	AREA Lab, CODE, READWRITE
;	ENTRY
;	LDR			r0,A			;Put 5 into register 0
;	LDR			r1,B			;Put 4 into register 1
;	LDR			r2,C			;Put 3 into register 2
;	LDR			r3,D			;Put 2 into register 3
;	LDR			r4,E			;Put 1 into register 4
;	MUL			r5,r3,r4		;Multiply values stored in register 3 and 4 and store the result into register 5
;	SUB			r5,r2,r5		;Subtract the value stored in register 2 by the value in register 5 and store the result into register 5
;	ADD			r5,r5,r1		;Add the value stored in register 1 to the value stored in register 5 and store the result into register 5
;	ADD			r5,r5,r0		;Add the value stored in register 0 to the value stored in register 5 and store the result into register 5
;	STR			r0,Z			;Store the value in register 0 into memory location Z
;A	DCB			4				;Put 4 in memory location A
;B	DCB			12				;Put 12 in memory location B
;C	DCB			-2				;Put -2 in memory location C
;D	DCB			-5				;Put -5 in memory location D
;E	DCB			-9				;Put -9 in memory location E
;Z	DCB			0				;Put 0 in memory location Z
;	END
	
;Lab 2
;Q1
;	AREA Lab, CODE, READONLY
;	ENTRY
;	MOV			R0, pc			;Place the next instructions into the contents of register 0
;	END
;Value of R0 was 0x00000008 --> PC = 0x00000008

;Q2
;	AREA Lab, CODE, READWRITE
;	ENTRY
;	LDR R1, [R0]
;	LDR R2, = 0x12345678
;	LDR R3, = 0x12
;	END

;Q3
;	AREA Lab, CODE, READWRITE
;	ENTRY
;	LDR R3, X
;	LDR R4, =X
;	ADR R5, X
;loop B	loop
;X	DCD 0x70707070
;	END

;Q4
;	AREA Lab, CODE, READONLY
;	ENTRY
;	LDR R0, = 0x12345678
;loop B	loop
;	AREA Lab, READONLY
;X	DCD 0x70707070
;	END
		
;	AREA Lab, CODE, READONLY
;	ENTRY
;	LDR	R0, = 0x12345678
;loop B	loop
;X	DCD	0x70707070
;	END

;Lab 3
;Q1
;	AREA Pointers, CODE, READONLY
;	ENTRY
;	ADR 	r0,List 	;register r0 points to List
;	MOV 	r1,#5 		;initialize loop counter in r1 to 5
;	MOV 	r2,#0 		;clear the sum in r2
;Q1a	ADD		r2,r2,r3	
;Q1a	Loop LDR	r3, [r0, #4]! 
;Loop LDR 	r3,[r0]		;copy the element pointed at by r0 to r3
;	ADD 	r0,r0,#4 	;point to the next element in the series
;	ADD 	r2,r2,r3 	;add the element to the running total
;	SUBS 	r1,r1,#1 	;decrement to the loop counter
;	BNE Loop 			;repeat until all elements added
;Endless B Endless		;infinite loop
;List DCD 	3,4,3,6,7 	;the numbers to be added together
						;each one is 4 bytes (20 bytes in total)
;	END	

;Q2
;	AREA Lab3, CODE, READWRITE
;	ENTRY
;	MOV r0, #2
;	AND	r0, #2_1
;	LSLEQ r1, r1, #8
;	LSREQ	r2, r2, #8

;	LSRNE r1, r1, r0	
;	LSLNE r2, r2, r0

;	ADD r4, r1, r2, r3, LSR #4	
;	END

;Q3
;	AREA Lab3, CODE, READWRITE
;	ENTRY
;	MOV R4, #2
;	ADD r4, r4, r4, LSL #14
;Loop B Loop
;	END

;Q4
;	AREA Lab3, CODE, READWRITE
;	ENTRY
;	MOV r2, #3
;	MOV r3, #3
;	AND r2, r3, #0x1080	;AND operation on r3 and operator 3, store result into r2
;Loop B Loop
;	END

;		AREA upc, CODE, READWRITE
;		ENTRY
;		LDR 	r0, =UPC		;Load UPC into register 0
;		MOV 	r1, #0			;Clear register 1 for storing sum of even position digits
;		MOV 	r2, #0			;Clear register 2 for storing sum of odd position digits
;		MOV 	r3, #0			;Incrementer for the loop
;		LDRB 	r4, [r0,#11]	;Store the Check Digit of the given UPC code
;		SUB		r4, r4, #48		;Convert ASCII letter to integer value
		
;Loop	LDRB 	r5, [r0,R3]		;Load register 5 with a byte from register 3
;		SUB		r5, r5, #48		;Convert the byte from an ASCII code to an integer value
;		ADD		r6, r6 ,r3		;Store content of r3 into r6
;		ANDS	r6, #2_1		;Check to see if the position is odd
;		BEQ 	EVEN			;Branch to go if position is even
;		ADD 	r2, r2, r5		;Odd position branch: add the integer into register 1
;		B 		Done			;Branch off to go to the next part of the loop
;EVEN 	ADD 	r1, r1, r5		;Even position branch: Add the integer into register 2
;Done 	ADD 	r3, r3, #1		;Increment register 3 by one to go to the next position
;		MOV		R6, #0			;Reset the contents of r6
;		CMP 	r3, #11			;Compare the value in register 3 to constant 12 to check if its the end of the UPC code
;		BNE 	Loop			;Loop back upwards to the label
	
;		ADD r1, r1, LSL #1 		;Mulitply contents of r1 by three
;		ADD r1, r1, r2			;Add the odd and even position sums and store the result into register 1
;		SUB	r1, r1, #1			;Subtract the content of register 1 by 1 and store it back in
		
;Repeat	CMP	r1, #10				;Compare the value in register 1 and 10
;		BGT Remain				;Branch if the value in register 1 is greater than 10
;		B Out					;Branch out of the loop if the value in register 1 is less than 10
;Remain 	SUB r1,r1,#10			;Subtract the value in register 1 by 10
;		B Repeat				;Repeat the loop
		
;Out		RSB r1, r1, #9			;Subtract 9 from the remainder in register 1 to get the calculated check digit. Store it in register 1.
;		CMP r1, r4				;Check if the calculated check digit is equal to the actual check digit
;		BEQ	RIGHT
;		MOV r0, #2
;		B	Endless
;RIGHT	MOV r0, #1
	
;Endless B Endless
;	AREA upc, DATA, READONLY
;UPC	DCB		"013800150738"		;UPC string	
;	END

;q1
;		AREA Lab4, CODE, READWRITE
;		ENTRY
;		
;		ADDPL r2, r1, r0, LSL #5
;		RSBHI r4, r3, #0x990
;		MOV r4, #2_10011001
;		MOV r3, #2_00001001
;	ANDS  r6, r5, #0x00FF
;		MOV r1, #2176
;		END
;q3
;		AREA Lab4, CODE, READWRITE
;		MOV r1, #2
;		MOV r0, #1
;comp	CMP r0, r1
;		BEQ stop
;		BGT	sub1
;		BLT sub2
;sub1	SUB r0, r0, r1
;		B	comp
;sub2	SUB r1, r1, r0
;		B 	comp
;stop	END

;		AREA Lab4, CODE, READWRITE
;		MOV r1, #2
;		MOV r0, #1
;back	CMP r0, r1
;		SUBGT	r0, r0, r1 
;		SUBLT	r1, r1, r0
;		B back
;stop	END

;Examples
;		AREA Example, CODE, READONLY
;		ENTRY
;		MOV r0, #1
;		MOV r1, #9
;		MOV r2, #3
;		MOV r3, #8
;		ADD	r4, r1, r0
;		ADDS r5, r2, r3
;Endless	B	Endless
;		END

;		AREA prog1, CODE, READONLY
;		ENTRY
;		AND r2,r3, #0x1080
;		neg r0, r1
;		ldr r0, [r1]
;		ldr r0, =0xff
;		ldr r0, =0xfff
;		ldr r0, X
;		ldr r0, =X
;		adr r0, X
;loop	B	loop
;		AREA prog1, DATA, READWRITE
;X		DCD	0xAAAAAAAA
;		END

;Lab 5
;Q1 and Q2
			AREA Lab5, CODE, READONLY
			ENTRY
			ADR SP, RegStack
main 		ADR r0,a 				;r0 is a pointer to array a
			ADR r1,endOfArray 		;address at the end of the array
			SUB r1,r1,r0 			;number of the array bytes
			ASR r1,#2 				;r1: length of the array,i.e., n
			MOV r2,#0 				;r2: outer loop counter,i.e., i
			SUB r4,r1,#1 			;r4 is (n - 1)
startOuter 	CMP r2,r4 				;compare i with (n - 1)
			BGE endOuter 			;if i >= (n - 1), then exit the outer loop
			MOV r3,#0 				;r3 is the inner loop counter, i.e., j
			SUB r5,r4,r2 			;r5 is (n - 1 - i)
startInner 	CMP r3,r5 				;compare j with (n - 1 - j)
			BGE endInner 			;if j >= (n - 1 -j), then exit the inner loop
			ADD r6,r0,r3,LSL#2		;r6 is a pointer to a[j]
			ADD r7,r6,#4 			;r7 is a pointer to a[j+1]
			BL sortTwo 				;call sortTwo(*a[j],*a[j+1])
			ADD r3,r3,#1 			;increment inner counter j
			B startInner 			;loop again (inner loop)
endInner	ADD r2,r2,#1 			;increment outer counter i
			B startOuter 			;loop again (outer loop)
endOuter 	B endOuter

sortTwo		STMEA	sp!, {R0-R4, R6, R7, LR} ;Store registers to stack pointer
			LDR 	R0, [sp, #-4]
			LDR 	R1, [R0]
			LDR		R2, [sp, #-8]
			LDR		r3, [r2]
			MOVMI R4, R3
			STRMI R1, [R2]
			STRMI R4, [R0]
			LDMEA sp!, {R0-R4, R6, R7, PC}	;Restore the working registers 
			AREA Lab5, DATA, READWRITE
a			DCD 44,-56,3,65,-8,32,6,-87,54,65,87,32,65
endOfArray 	SPACE 1
			ALIGN
RegStack	SPACE 128
			END

;Q3 and Q4
;			AREA Lab5, CODE, READONLY
;			ENTRY
;main 		ADR r0,a 				;r0 is a pointer to array a
;			ADR r1,endOfArray 		;address at the end of the array
;			ADR r13, a				;Set up the stack pointer
			
;			SUB r1,r1,r0 			;number of the array bytes
;			ASR r1,#2 				;r1: length of the array,i.e., n
;			MOV r2,#0 				;r2: outer loop counter,i.e., i
;			SUB r4,r1,#1 			;r4 is (n - 1)
;startOuter 	CMP r2,r4 				;compare i with (n - 1)
;			BGE endOuter 			;if i >= (n - 1), then exit the outer loop
;			MOV r3,#0 				;r3 is the inner loop counter, i.e., j
;			SUB r5,r4,r2 			;r5 is (n - 1 - i)
;startInner 	CMP r3,r5 				;compare j with (n - 1 - j)
;			BGE endInner 			;if j >= (n - 1 -j), then exit the inner loop
;			ADD r6,r0,r3,LSL#2		;r6 is a pointer to a[j]
;			ADD r7,r6,#4 			;r7 is a pointer to a[j+1]
;			BL sortTwo 				;call sortTwo(*a[j],*a[j+1])
;			ADD r3,r3,#1 			;increment inner counter j
;			B startInner 			;loop again (inner loop)
;endInner	ADD r2,r2,#1 			;increment outer counter i
;			B startOuter 			;loop again (outer loop)
;endOuter 	B endOuter

;sortTwo		CMP 	r6, r7		;Compares the values in register 6 and 7
;			EORGT	r6, r6, r7	;If the value in R6 is greater than the value in register 7, then switch the values.
;			EORGT	r7, r6, r7
;			EORGT	r6, r6, r7
;			BX		lr	
;			AREA Lab5, DATA, READWRITE
;a			DCD 44,-56,3,65,-8,32,6,-87,54,65,87,32,65
;endOfArray  SPACE 1
;			END