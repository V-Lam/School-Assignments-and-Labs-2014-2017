male(jonathan).
male(straizo).
male(dio).
male(zeppeli).
male(jeorge).
male(gio).
male(joseph).
male(caesar).

female(erina).
female(elizabeth).
female(mariah).
female(roberta).
female(lisa).
female(susan).
female(suzy).
female(holly).

parent(jonathan, jeorge).
parent(erina, jeorge).
parent(straizo, lisa).
parent(elizabeth, lisa).
parent(dio, gio).
parent(mariah, gio).
parent(zeppeli, susan).
parent(roberta, susan).
parent(jeorge, joseph).
parent(lisa, joseph).
parent(gio, suzy).
parent(susan, suzy).
parent(jeorge, caesar).
parent(lisa, caesar).
parent(joseph, holly).
parent(suzy, holly).

married(jonathan, erina).
married(erina, jonathan).
married(straizo, elizabeth).
married(elizabeth, straizo).
married(dio, mariah).
married(mariah, dio).
married(zeppeli, roberta).
married(roberta, zeppeli).
married(jeorge, lisa).
married(lisa, jeorge).
married(gio, susan).
married(susan, gio).
married(joseph, suzy).
married(suzy, joseph).

father(F, C) :- male(F), parent(F, C).
mother(M, C) :- female(M), parent(M, C).
sibling(A,B):- mother(M,A),mother(M,B),father(F,A),father(F,B),A\=B.
brother(A, B):- male(A), sibling(A, B).
grandson(A, B):- male(A), parent(P,A), parent(B,P).
cousin(A, B):- parent(P,A), parent(Q,B), sibling(P,Q).
mother_in_law(M, A):- married(A, B), mother(M, B).

descendant(D, A):- parent(A, D).
descendant(D, A):- parent(P, D), descendant(P, A).

