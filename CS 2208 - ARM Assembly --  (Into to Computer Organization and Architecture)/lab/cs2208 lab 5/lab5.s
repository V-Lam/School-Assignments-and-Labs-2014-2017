	AREA lab5, CODE, READONLY
	ENTRY
	
main ADR r0,a ;r0 is a pointer to array a
		ADR r1,endOfArray ;address at the end of the array
		SUB r1,r1,r0 		;number of the array bytes
		ASR r1,#2 			;r1: length of the array,i.e., n
		MOV r2,#0 			;r2: outer loop counter,i.e., i
		SUB r4,r1,#1 		;r4 is (n - 1)
startOuter CMP r2,r4 		;compare i with (n - 1)
		BGE endOuter 			;if i >= (n - 1), then exit the outer loop
		MOV r3,#0 				;r3 is the inner loop counter, i.e., j
		SUB r5,r4,r2 			;r5 is (n - 1 - i)
startInner CMP r3,r5 			;compare j with (n - 1 - j)
		BGE endInner 			;if j >= (n - 1 -j), then exit the inner loop
		ADD r6,r0,r3,LSL#2			;r6 is a pointer to a[j]
		ADD r7,r6,#4 				;r7 is a pointer to a[j+1]
		
		
		BL sortTwo 					;call sortTwo(*a[j],*a[j+1])
		ADD r3,r3,#1 				;increment inner counter j
		B startInner 			;loop again (inner loop)
endInner ADD r2,r2,#1 			;increment outer counter i
		B startOuter 		;loop again (outer loop)
endOuter B endOuter

;------------------------------
	;r6 is a pointer to a[j]
	;r7 is a pointer to a[j+1]
	;sortTwo(*a[j],*a[j+1])
	;a[j] is x. a[j+1] is y
;	sortTwo STMEA sp!, {r6,r7}		;save working registers
;		LDR r8,[r6]	;load contents from pointers
;		LDR	r9,[r7]
;		CMP	r8,r9				;compare and see if *y < *x
		
;		STRGT r8,r6	;if so swap *x and *y via temp variable
;		STRGT r9,r7
		
		;return
;		LDR r12,[r13],#4;get saved PC and post-increment stack pointer
;		SUB r15,r12,#4	;fix PC and load into r15 to return	
;------------------------------


sortTwo		STMEA	sp!, {R0-R4, R6, R7, LR} ;Store registers to stack pointer
			LDR 	R0, [sp, #-4]
			LDR 	R1, [R0]
			LDR		R2, [sp, #-8]
			LDR		r3, [r2]
			MOVMI R4, R3
			STRMI R1, [R2]
			STRMI R4, [R0]
			LDMEA sp!, {R0-R4, R6, R7, PC}	;Restore the working registers 
			




a DCD 44,-56,3,65,-8,32,6,-87,54,65,87,32,65
endOfArray SPACE 1
	
	END