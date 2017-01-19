;Vivian Lam
;program that concatenates two strings into another string
		AREA prog1, CODE, READONLY
        ENTRY
        LDR r0, =STRING1 ;load address of string1 into register 0
        LDR r1, =STRING2 ;load address of string2 into register 1
        LDR r3, =STRING3 ;load address of string3 into register 3
		LDR r5, EoS1;make r5 point to the EoS character

loopStr1 	LDRB r4, [r0], #1 ;load the first bit of string1 into r4
			
			;check if null character first
			CMP r4, #0x00  	  	;check to see if we reached end of the string
			BEQ loopStr2		;if null then go to next loop
			STRB r4, [r3], #1 	;otherwise store that character in r4 into string3
			B loopStr1 		;continue looping if haven't reached EoS character

loopStr2   	LDRB r4, [r1], #1 ;load the first bit of string1 into r4
			CMP r4, #0x00  	  ;check to see if we reached end of the string
			BEQ theEnd			;if null then we are done copying the strings
			STRB r4, [r3], #1 ;store that character in r4 into string3
			B loopStr2		;continue looping if haven't reached EoS character


theEnd
        LDRB r4, [r5], #1 ;load and store the EoS null character into string3 to signal the end of the string
        STRB r4, [r3]

loop b loop;infinite loop so no error

        ALIGN
STRING1 DCB "This is a test string1"            ;String1
EoS1    DCB 0x00                                ;end of string1
STRING2 DCB "This is a test string2"            ;String
EoS2    DCB 0x00                                ;end of string2
STRING3 space 0xFF 	
        END
