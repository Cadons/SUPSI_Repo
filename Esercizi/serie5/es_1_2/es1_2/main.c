// es1.cpp : Questo file contiene la funzione 'main', in cui inizia e termina l'esecuzione del programma.
//es1 + es2

#include <stdio.h>
#include <stdlib.h>
struct frazione
{
    long num, den;
};
typedef struct frazione Frazione;
Frazione creaFrazione(long num, long den);
Frazione semplifica(Frazione f);
void stampaFrazione(Frazione f);
Frazione somma(Frazione f1, Frazione f2);
Frazione sottrazione(Frazione f1, Frazione f2);
Frazione mult(Frazione f1, Frazione f2);
Frazione divide(Frazione f1, Frazione f2);
long MCD(long a, long b);
long mcm(long a, long b);
void scriviFile(FILE* f,Frazione * data);
Frazione leggiFile(FILE * f);
int main()
{
    FILE* file;
    Frazione f1;
    Frazione f2;
    f1 = creaFrazione(16, 7);
    f2 = creaFrazione(21, 5);
//	stampaFrazione(semplifica(f1));
    printf("\n");
    printf("Somma: ");
    stampaFrazione(f1); printf("+"); stampaFrazione(f2); printf("=");
    stampaFrazione(somma(f1, f2));
    printf("\n");
    printf("Sottrai: ");
    stampaFrazione(f1); printf("-"); stampaFrazione(f2); printf("=");
    stampaFrazione(sottrazione(f1,f2));
    printf("\n");

    printf("moltiplica: ");
    stampaFrazione(f1); printf("*"); stampaFrazione(f2); printf("=");
    stampaFrazione(mult(f1,f2));
    printf("\n");

    printf("dividi: ");
    stampaFrazione(f1); printf(" / "); stampaFrazione(f2); printf(" = ");
    stampaFrazione(divide(f1, f2));
    printf("\n");
    printf("Salvo su file\n");
    file=fopen("output.bin", "w");
    scriviFile(file,&f1);
    scriviFile(file,&f2);

    fclose(file);
    printf("Leggo da file\n");
    file=fopen("output.bin", "r");
    f1=leggiFile(file);
    f2= leggiFile(file);
    stampaFrazione(f1);
    printf(" + ");
    stampaFrazione(f2);
    printf(" = ");
    stampaFrazione(somma(f1,f2));
    printf("\n");
    fclose(file);
}
void scriviFile(FILE* f,Frazione * data)
{
    if(f!=NULL)
    {
        fwrite(data,sizeof (Frazione),1,f);
    }
}
Frazione leggiFile(FILE * f)
{
    void *output= malloc(sizeof (Frazione));
    if(f!=NULL)
    {
        fread(output,sizeof(Frazione),1,f);
    }
    return *((Frazione*)output);
}
long MCD(long a, long b)
{
    b = abs(b);
    a = abs(a);
    if (b >a)
    {
        long t = a;
        a = b;
        b = t;
    }
    if (b == 0||a==b)
    {
        return a;

    }
    return MCD(b, a % b);
}
long mcm(long a, long b)
{
    return (a * b) / MCD(a, b);
}
Frazione creaFrazione(long num, long den)
{
    Frazione fraz;//crea la struct sullo stack, malloc crea spazio nello heap
    fraz.num = num;
    fraz.den = den;
    return fraz;
}

Frazione semplifica(Frazione f)
{

    long mcd = MCD(f.num, f.den);
    if (mcd != 0)
    {
        f.num /= mcd;
        f.den /= mcd;

    }

    return f;
}

void stampaFrazione(Frazione f)
{
    printf("%ld/%ld", f.num, f.den);
}

Frazione somma(Frazione f1, Frazione f2)
{
    Frazione somma;
    somma.den = mcm(f1.den, f2.den);
    somma.num = (somma.den / f1.den) * f1.num + (somma.den / f2.den) * f2.num;//
    return somma;
}

Frazione sottrazione(Frazione f1, Frazione f2)
{
    f2.num *= -1;
    return somma(f1,f2);
}

Frazione mult(Frazione f1, Frazione f2)
{
    Frazione mult;
    mult.num = f1.num * f2.num;
    mult.den = f1.den * f2.den;
    mult = semplifica(mult);
    return mult;
}
Frazione invert(Frazione f)
{
    long tmp = f.num;
    f.num = f.den;
    f.den = tmp;
    return f;
}
Frazione divide(Frazione f1, Frazione f2)
{

    return mult(f1,invert(f2));
}
