set D;
set M;

param data{M,D};
param durata{M};
param c{D};
param ci{D};
param Q;

var x{M,D}>=0;
var y{M,D}>=0;/*qt di dati immagaziadati DOPO la missione m*/
