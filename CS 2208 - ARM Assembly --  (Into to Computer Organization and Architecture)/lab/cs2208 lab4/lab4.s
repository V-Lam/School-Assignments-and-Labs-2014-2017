;QUES1
;	AREA lab4, CODE, READONLY
;	ENTRY
	
	;add 32 times the content of r0 and r1 only if N is clear. store in r2
;	ADDPL r2, r1, r0, LSL#5 ;shifting by 5 is the same as multiplying it by 2^5=32
	
	;subtract the content of r3 from 0x990, put result in r4 only if C is clear and Z is clear
;	RSBHI r4, r3, #0x990
	
	;clear the second significant byte of the content of r5 (store 8 zeroes in it) and put result in r6
;	BICS r6, r5, #0xF0
	
;	END
		

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;ques 3: A) tranditional assembly, only branches can be conditional (dont use ARM conditional execution feature)
;	AREA lab4, CODE, READONLY
;	ENTRY
	
;	MOV r0,#6;test values
;	MOV r1,#4
	
;	CMP	r0,r1	;is [r0]==[r1]
;	BEQ Stop	;if so stop
		
;	CMP	r0,r1	;is [r0]>[r1]
;	BGT True	;if so then [r0] <- [r0] – [r1]
;	B False	;if not then [r0] <- [r1] – [r0] 

;True	;if so then [r0] <- [r0] – [r1]
;	SUB r0, r0, r1
;	B Stop

;False	;if not then [r0] <- [r1] – [r0] 
;	SUB r0, r1, r0

;Stop B Stop
;	END


;ques 3: B) ARM assembly, all instructions are conditional, thus improving code density
	AREA lab4, CODE, READONLY
	ENTRY


	CMP r0, r1;
	;check if r0>r1
	;if so then [r0] <- [r0] – [r1]
	SUBGT r0, r0, r1
	
	;if not then [r0] <- [r1] – [r0] 
	SUBLT r0, r1, r0
	
	
	END
