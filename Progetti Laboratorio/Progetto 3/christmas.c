#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

//Somma massima (caso pessimo 100*100=10000)
#define MAXN 10000

//Dati in input
int N, B, i, j;
int V[MAXN];

//Metodo per calcolare il totale la spesa totale di babbo natale
int greedy_santa()
{
    //Array utilizzato per calcolare tutte le somme possibili.
    //L'indice dell'array sono le somme e viene impostato a 1 a tutte le somme ammissibili.
    int sums[MAXN] = {0};
    
    //Variabile di supporto per cacolare la somma di tutti i regali.
    int sum = 0;

    //Variabile di ritorno della somma spesa da babbo natale.
    int spesa = 0;

    //Ciclo tutti i regali
    for(j=0; j<N; j++){
        //Incremento la somma totale dei regali.
        sum+=V[j];
        //Eseguo un for che parte da Budget e arriva a 0. 
        //Parto da budget perché è inutile sommare altri regali già al di fuori del budget.
        for(i=B; i>=0; i--){
            //Controllo se è gia presente una somma calcolata precdentemente al di sotto del budget.
            if (sums[i]==1) {
                //Se è presente gia una somma aggiungo all'indice il valore dell'ultimo regalo 1.
                sums[i+V[j]]=1;
            //Se il valore singolo del regalo non è ancora predente lo aggiungo all'array si somme.
            } else if (sums[i]==0 && i==V[j]) {
                sums[i]=1;
            }
        } 
    }

    //Se la somma totale di tutti i regali non supera il budget, imposto questa come spesa di babbo natale.
    if(sum<=B) {
        spesa = sum;
    } else {
        //Altrimenti trovo la prima somma trovata pratire da budget.
        for(int i=B; MAXN; i++){
            if(sums[i]==1){
                spesa=i;
                break;
            }
        }
    }
    return spesa;
}


int main() {

    assert(2 == scanf("%d%d", &N, &B));
    for(i=0; i<N; i++) {
        assert(1 == scanf("%d", &V[i]));
    }

    printf("%d\n", greedy_santa());
    return 0;

}
