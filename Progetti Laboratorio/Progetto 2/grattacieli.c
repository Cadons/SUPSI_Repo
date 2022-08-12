#include <stddef.h>
#include <limits.h>

typedef struct
{
    int a;
    int b;
    int c;
    struct Vincolo *next;
} Vincolo;

Vincolo *head = NULL;
Vincolo *current = NULL;

void insert(int a, int b, int c) {
   //create a link
   Vincolo *link = (Vincolo*) malloc(sizeof(Vincolo));
	
   link->a = a;
   link->b = b;
   link->c = c;
	
   //point it to old first node
   link->next = head;
	
   //point first to new first node
   head = link;
}

//display the list
/*void printList() {
   Vincolo *ptr = head;
   printf("\n[ ");
	
   //start from the beginning
   while(ptr != NULL) {
      printf("(%d,%d,%d) ",ptr->a,ptr->b,ptr->c);
      ptr = ptr->next;
   }
	
   printf(" ]");
}*/

/*int length() {
   int length = 0;
   Vincolo *current;
	
   for(current = head; current != NULL; current = current->next) {
      length++;
   }
	
   return length;
}*/

long long getMin(int N, long long* H, int alm) {
    long long min = LLONG_MAX;
    
    for (int i = 0; i < N; i++)
    {
        if (min >= H[i] && H[i] >= alm) 
        {
            min = H[i];
        }
    }

    return min;
}


long long costruisci(int N, int M, long long* H, int* A, int* B, int* C) {
    for (int i = 0; i < M; i++)
    {
        insert(A[i], B[i], C[i]);
    }    
    
    int check = 1;
    long long minSupp;
    Vincolo *pre;
    long long min = getMin(N, H, 1);

    while (check) {
        check = 0;
        min = getMin(N, H, minSupp);
        minSupp = LLONG_MAX;
        for(current = head; current != NULL; current = current->next) {
            if (H[current->b] > H[current->a] + current->c)
            {
                H[current->b] = H[current->a] + current->c;
                check = 1;
            }
            if (minSupp >= H[current->a]) 
            {
                minSupp = H[current->a];
            }
            if(H[current->a] == min && pre != NULL) {
                pre->next = current->next;
            } else if (H[current->a] == min && pre == NULL) {
                head = current->next;
            }
            pre = current;
        } 
        pre = NULL;
    }
    
    int tot=0;
    for (int i = 0; i < N; i++)
    {
        tot+=H[i];
    }
    return tot;
}