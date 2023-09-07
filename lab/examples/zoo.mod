/*Insiemi*/
set A;
set P;
set S;

/*parametri*/
param c{P};
param e{A};
param f{A,S};
param k{S,P};

/*variabili*/
var x{P}>=0;

/*funzione obbiettivo*/
minimize z: sum{p in P} c[p] * x[p];


/*vincoli*/
 /*questa riga impone il per ogni sia a destra che a sinistra della disequazione*/
 /*divido per cento perchè i dati percentuali non sono tra 0 e 1*/
s.t. fabb{s in S}:
	sum {p in P} k[s,p]*x[p]/100 
	>=
	sum {a in A} e[a] * f[a,s]