;Vivian Lam
;program that calls a subroutine to perform a calculation, clips the result if it's > d, and then return and double that return value and store in r1		

		AREA prog3, CODE, READONLY
        ENTRY
;---------------------------------------------------
Main
		MOV r0, #3 ;store a value in r0
		;MOV r2, #1		;test value to see if register is restored after funciton call
		ADR SP, Stackk	;initialize pointer to stack
		BL Function1 ;jump to the function
		
		ADD r1, r0, r0 ;double the new value of r0 and store it into r1
		
loop B loop		;infinite loop so no error

;---------------------------------------------------
;performs the calculation: y = a × x^2 + b × x + c 
;where x is r0. the return value is stored in r0
	;use r2-r5 to store a,b,c,d (respectively)
	;use r6 to store x^2
	;use r8 to store b × x
	;use r7 to store current output value
Function1 	STMIA SP!, {r2-r8};store working registers and link register
		
			LDR r2, memA ;load values a,b,c,d into registers r2-r5 (make r2 point to memA etc.)
			LDR r3, memB 
			LDR r4, memC 
			LDR r5, memD 
	
		;perform calculation: y = a × x^2 + b × x + c 
			MUL r6, r0, r0		;y=x^2
			MUL r7, r6, r2		;y=a × y
			MUL r8, r3, r0		;b × x
			ADD r7, r7, r8		;y = y+ b × x
			ADD r7, r7, r4		;y=y+c
			
			MOV r0, r7 	;store the output value to r0
			CMP r0, r5	;check to see if the result is > value of d
			MOVGT r0, r5;if so then the output value will be clipped to d and is stored in r0
			;otherwise the output value is not clipped and is stored in r0

		LDMDB SP!, {r2-r8};restore the working registers back to normal
		MOV PC,LR				;return from function 1 by making the value of PC equal to the link register
;---------------------------------------------------		

memA 	DCD 5	;values for a, b, c, d, which are used in the function and for clipping
memB 	DCD 6
memC 	DCD 7
memD 	DCD 10
Stackk	space 0xFF ;space for stack
		ALIGN
		END
