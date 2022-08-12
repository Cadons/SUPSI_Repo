#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
int bottom_up_cutrod(int p[], int n);
int main() {
    printf("Hello, World!\n");
    double t = clock();
    int v[]={1,2,3,4,5,6,7,8,9};
    bottom_up_cutrod(v,9);
    t = clock() - t;
    printf("%lf", t);
    return 0;
}

int bottom_up_cutrod(int p[], int n) {
    int r[n];
    for (int j = 0; j < n; j++)
        r[j] = 0;

    r[0] = p[0];
    for (int j = 1; j < n; j++) {
        int q = INT_MIN;
        for (int i = 0; i < j; i++) {
            q = max (q, p[i] + r[j - i - 1]);
        }
        r[j] = q;
    }
    return r[n - 1];
}
