;Vivian Lam
;program to determine whether a string of 12 ASCII encoded digits stored in memory
;is a valid UPC or not. If valid, store 1 in r0. if not, store 2 in r0.

	AREA prog1, CODE, READONLY
	ENTRY
		
	;HINT 1: You can implement the division operation using repeated subtractio
	;HINT 2: To calculate 3 × Z, you can do so using only one ADD instruction with LSL#1 shift.
	;HINT 3: To load a byte to a register, use LDRB not LDR.
	
	;r0 points to UPC
	;r1 points to the position in UPC
	;r2 for getting the current byte at that position
	;r3 is the total for the first sum
	;r4 is the total for the second sum
	;convInt is a constant used for converting ASCII into integer

	ADR r0, UPC 		;r0 to point to UPC
convInt EQU 48 			;Constant for converting ASCII into integer (subtract 48)

;compute first sum
AddOdd 					;Loop to add numbers in odd positions
	LDRB r2,[r0,r1] 	;load byte into register. the loaded byte is the byte r1 points to in UPC
	SUB r2,#convInt 	;convert that byte ASCII into int (subtract the constant)
	ADD r3, r2 			;add integer value to total (first sum)
	ADD r1, #2 			;point to next odd digit (increase counter by 2)
	CMP r1, #12 		;loop condition: check if r1 pointer is outside of UPC (12 because 12 digits)
	BNE AddOdd 			;loop UNTIL we have iterated enough to add all the digits at odd positions
	
	
	MOV r1, #1 			;Reset value of r1 to be 1 (this is so we can deal with even digits)
	
	
;compute second sum	 (add numbers in even positions)
AddEven 				;Looop
	LDRB r2,[r0,r1] 	;load byte into register. the loaded byte is the byte r1 points to in UPC
	SUB r2,#convInt 	;convert that byte ASCII into int(subtract the constant)
	ADD r4, r2 			;add integer value to total (second sum)
	ADD r1, #2 			;point to next even digit (increase counter by 2)
	CMP r1, #11 		;loop condition: check if r1 pointer is outside of UPC (11 because 12-1 digits)
	BNE AddEven			;loop UNTIL we have iterated enough to add all the digits at even positions
	
	
;multiply first sum by 3
	ADD r3, r3, r3, LSL #1 	;left shift to double the first sum and add first sum to it

;and add it to second sum (store in r4)
	ADD r4, r3

;subtract 1 (store in r4)
	SUB r4, #1;
	
;compute remainder when adjusted total is divided by 10
RptSub 				;LOOP until the remaining total is less than or equal to 9
	CMP r4,#9 		;check if total is less than or equal to 9
	SUBGT r4,#10 	;subtract 10 from total if greater than 9
	BHI RptSub 		;end loop
	
;subtract remainder form 9	
	RSB r5,r4,#9 	;Subtract the remainder from 9, store in r5 (calculated check digit)
	LDRB r6,[r0,r1] ;Load UPC check digit into r6 (r1 is already pointing at check digit position)
	SUB r6,#convInt ;Subtract 48 from check digit's ASCII value to obtain its integer value
	MOV r0,#2 		;Set r0 to 2 - if check digits match this will change to 1 else, stays 2
	
;check result	
	CMP r5,r6 		;Compare UPC check digit with calculated check digit valid, store 1 in r0 invalid, store 2 in r0
	MOVEQ r0,#1 	;store 1 in r0 if check digits match and UPC is valid


Loop B Loop ;Infinite loop to prevent error	
	
;test values	
;UPC DCB "013800150738" ;UPC String	
;UPC DCB "060383755577" ;UPC String	
UPC DCB "065633454712" ;UPC String	


	END