	;ques4
	AREA prog1, CODE, READONLY
	ENTRY
	LDR r0, = 0x12345678
loop b loop
	AREA lab2, READONLY
X	DCD 0x70707070
	END
	