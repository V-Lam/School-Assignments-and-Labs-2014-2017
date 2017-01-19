;ques1
;	AREA pointers, CODE, READONLY
;	ENTRY
	
;Start ADR r0,List ;register r0 points to List ORIGINAL
;Start ADR r0,List ;POST INDEX
;Start ADR r0,List -4 ;PRE INDEX

;	MOV r1,#5 ;initialize loop counter in r1 to 5
;	MOV r2,#0 ;clear the sum in r2
	
;Loop LDR r3,[r0] ;copy the element pointed at by r0 to r3
;Loop LDR r3,[r0]; Autoindexing pre-indexed addressing mode
;Autoindexing post-indexed addressing mode 


;;	ADD r0,r0,#4 ;point to the next element in the series
;	ADD r2,r2,r3 ;add the element to the running total
;	SUBS r1,r1,#1 ;decrement to the loop counter
;	BNE Loop ;repeat until all elements added

;Endless B Endless ;infinite loop

;List DCD 3,4,3,6,7 ;the numbers to be added together
					;each one is 4 bytes (20 bytes in total)
;	END



;ques2
	AREA pointers, CODE, READONLY
	ENTRY
	ANDS r4,r0#1	;is r0 even, set flag 
	BEQ NO

YES
	ADD r1, LSR r0;if so shift: [r1] <- [r1] >> [r0] and [r2] <- [r2] << [r0]
	ADD r2, LSL r0
	B
NO;its even
	ADD r1, LSL #8;if not: [r1] <- [r1]  <<  8 and [r2] <- [r2]  >>  8
	ADD r1, LSL #8
	
GANGURO	
	ADD r4, r1, r2	;[r4] <- [r1] + [r2] + ([r3] ÷ 16)
	ADD r4, r3 LSR# 4
	
Loop B loop
	END



;ques3

;[R4] <- 16385 × [R4].


;ques4


;ques5


;ques6