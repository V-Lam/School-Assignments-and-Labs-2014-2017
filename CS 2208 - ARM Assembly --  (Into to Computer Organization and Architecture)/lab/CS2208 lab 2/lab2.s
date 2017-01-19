;ques1
	;AREA lab2, CODE, READONLY
	;ENTRY
	;MOV r0, pc
	;END
		
	;ques2
	;AREA lab2, CODE, READONLY
	;ENTRY
	;LDR r1, [r0]
	;LDR r2, = 0x12345678
	;LDR r3, = 0x12
	;END
		
	;ques3
;	AREA lab2, CODE, READONLY
;	ENTRY
;	LDR r3, X
;	LDR r4, =X
;	ADR r5, X
;loop B loop
;X DCD 0x70707070
;	END
		
	;ques4
	;AREA prog1, CODE, READONLY
	;ENTRY
	;LDR r0, = 0x12345678
;loop b loop
;	AREA lab2, READONLY
;X	DCD 0x70707070
;	END
	
;	AREA prog2, CODE, READONLY
;	ENTRY
;	LDR R0, = 0X12345678
;loop b loop
;X DCD 0x70707070
;	END