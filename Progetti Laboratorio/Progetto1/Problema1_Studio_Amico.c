#include <stdbool.h>
// Declaring functions
bool associabili(int N, int *v1, int *v2)
{
    if (sizeof(v1) != sizeof(v2))
        return false;
    int voti2[10] = {0};
    int voti5[10] = {0};
    for (int i = 0; i < N; i++)
    { //n
        voti2[v1[i] - 1]++;
        voti5[v2[i] - 1]++;
    }

    //Calcolo delle cumulate
        if(voti2[9] > 0 || voti5[0] > 0) return false;
    for (int i = 8; i >= 0; i--)
    {
        voti5[i] += voti5[i + 1];
        voti2[i] += voti2[i + 1];
    if (voti5[i+1]<voti2[i])
        return false;
    }
        
    
    return true;
    //O(n)
}