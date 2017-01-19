;Ques1
	;AREA example1, CODE, READONLY
	;ENTRY
	;MOV r1, #2 ;Put 2 in register r1
	;MOV r2, #3 ;Put 3 in register r2
	;MOV r3, #4 ;Put 4 in r3
	;MOV r$, #5 ;Put 5 in r4
	;MOV r5, #6 ;Put 6 in r5
	;;ADD r0, r1, r2 ;Add r1 to r2 and put the result in r0
	;ADD r0, r1, r2
	;ADD r0, r0, r3
	;ADD r0, r0, r4
	;ADD r0, r0, r5 ;Add r1-r5 and store in r0
	;END
		
;ques2		
	;AREA example1, CODE, READONLY
	;ENTRY
	;MOV r9, #9 ;Put 9 in r9
	;END
		
;ques3		
	;AREA example1, CODE, READONLY
	;ENTRY
	;MOV r0, #1
	;MOV r1, #2
	;MOV r2, #3
	;MOV r3, #4
	;MOV r4, #5
	;MUL r5, r3, r4 ;Store result in r5
	;SUB r5, r2, r5
	;ADD r5, r5, r1
	;ADD r5, r5, r0
	;END
		
;ques4		
	;AREA example1, CODE, READONLY
	;ENTRY
	;LDR r0, A ;memory location A into register 0
	;LDR r1, B
	;LDR r2, C
	;LDR r3, D
	;LDR r4, E
	;MUL r5, r3, r4 ;Store result in r5
	;SUB r5, r2, r5
	;ADD r5, r5, r1
	;ADD r5, r5, r0
	;STR r0, Z ;store in register 0 location memory Z
;loop	 b 	loop	
	;AREA example1, DATA, READWRITE
;A	DCD 4 ;put 4 into memory location A
;B	DCD 12	
;C	DCD -2
;D	DCD -5	
;E	DCD -9
;Z	DCD 0	
	;END
		
;ques5		
	AREA example1, CODE, READONLY
	ENTRY
	LDR r0, A ;memory location A into register 0
	LDR r1, B
	LDR r2, C
	LDR r3, D
	LDR r4, E
	MUL r5, r3, r4 ;Store result in r5
	SUB r5, r2, r5
	ADD r5, r5, r1
	ADD r5, r5, r0
	STR r0, Z ;store in register 0 location memory Z
loop b loop

	AREA example1, DATA, READWRITE
A	DCB 4 ;put 4 into memory location A
B	DCB 12	
C	DCB -2
D	DCB -5	
E	DCB -9
Z	DCB 0	
	END