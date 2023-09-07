var x{1..4}>=0;/*dichiaro 4 variabili x1,x2,x3,x4*/
maximize z: 10 * x[1]+ 12 * x[2] + 9 * x[3]+ 15 * x[4]; /*funzione obbiettivo*/
/*vincoli*/
subject to const_1:
	5* x[1] +8 * x[2] + 6* x[3] +13 *x[4]<=446.151;
subject to const_2:
	11* x[1] + 4 * x[2] + 15 * x[4]<= 200;
subject to const_3:
	3* x[1] +13 * x[3] + 19* x[4] <=100;