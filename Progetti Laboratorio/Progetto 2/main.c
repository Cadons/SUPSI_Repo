#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#define Nmax 100000

struct _node
{
    long long height;
    int lengthMax;
    long long maxHeights[Nmax];
};
int compare(void *a, void *b)
{
    return *((int *)a) - *((int *)b);
}
struct _mynode
{
    int id;
    long long startHeight;
    long long tmpHeight;
};
int getEditOperationForNode(int nodeId, int *B, int M)
{
    int cnt = 0;
    for (int i = 0; i < M; i++)
    {
        if (nodeId == B[i])
            cnt++;
    }
    return cnt;
}

int a[N][Nmax],reach[N],n;
void dfs(int v) {
	int i;
	reach[v]=1;
	for (i=1;i<=n;i++)
	  if(a[v][i] && !reach[i]) {
		printf("\n %d->%d",v,i);
		dfs(i);
	}
}

long long costruisci(int N, int M, long long *H, int *A, int *B, int *C)
{
    struct _mynode nodes[N];
    for (int i = 0; i < N; i++)
    {
        nodes[i].id = i;
        nodes[i].startHeight = H[i];
        nodes[i].tmpHeight = H[i];
    }

    for (int j = 0; j < N; j++)
    {
        printf("%d has %d constrains\n", nodes[j].id, getEditOperationForNode(nodes[j].id, B, M)); //ottieni quanti vincoli ci sono sul nodo
                                                                                                   //Bisogna analizzare i vincoli da cui dipende andandoli a valutare e di conseguenza vanno esclusi dall'analisi perchè già calcolati, se il vibcolo è 1 o 0  si può procedere con la procedura normale,
                                                                                                   //i vincoli sono analizzati dal primo nodo all'ultimo in ordine
        for (int i = 0; i < M; i++)
        {

            if (B[i] == nodes[j].id)
            {
                if (H[A[i]] + C[i] <= nodes[j].startHeight)
                {
                    //    printf("id:%d(vincolo:%d) -> %lld <? %lld:",nodes[i].id,i,nodes[i].tmpHeight,H[A[i]] + C[i]);
                    if (H[A[i]] + C[i] < nodes[j].tmpHeight)
                    {

                        nodes[j].tmpHeight = H[A[i]] + C[i];
                        H[B[i]] = nodes[j].tmpHeight;

                        /**ALERT
                            *Bisogna gestire i casi di modfiche degli altri nodi, bisognerebbe andare a cercare i nodi del vettore A calcoalre di conseguenza le altezze 
                            */
                    }
                }
            }
        }
    }

    for (int i = 0; i < N; i++)
    {
        printf("%d) %lld -> %lld\n", nodes[i].id, nodes[i].startHeight, nodes[i].tmpHeight);
    }
    /*
    struct _node graph[N];
    for (int i = 0; i < N; i++)
    {
        graph[i].height = H[i];
        int z = 0;
        for (int j = 0; j < M; j++)
        {
            if (B[j] == i)
            {
                if (H[A[j]] + C[j] <= graph[i].height)
                {
                    graph[i].maxHeights[z] = H[A[j]] + C[j];
                    printf("%d)%d\n", i + 1, H[A[j]] + C[j]);
                    z++;
                       graph[i].lengthMax = z;
                    qsort(graph[i].maxHeights, graph[i].lengthMax, sizeof(int), compare);

                }
            }
        }

        if (z > 0)
        {
            graph[i].lengthMax = z;
             printf("%d)constraint:%d\n",i+1,z);
        }

        else
        {
            graph[i].maxHeights[z] = graph[i].height;
            graph[i].lengthMax = 0;
            printf("%d)No constraints\n", i +1);
        }
                
                
    }



    for (int i = 0; i < N; i++)
    {
        printf("%lld -> %d:->%d\n", graph[i].height, graph[i].lengthMax, graph[i].maxHeights[0]);
    }
*/
    int tot = 0;
    for (int i = 0; i < N; i++)
    {
        tot += nodes[i].tmpHeight;
    }
    printf("---\n");
    return tot;
}

int main()
{
    // Input da file:
    // freopen("input.txt", "r", stdin);

    // Output su file:
    // freopen("output.txt", "w", stdout);

    int n, m; //n = numero grattacieli, m = numeri vincoli
    scanf("%d%d", &n, &m);
    long long *h = malloc(sizeof(long long) * n); //altezze
    for (size_t i = 0; i < n; i++)
    {
        scanf("%lld", &h[i]);
    }
    int *a = malloc(sizeof(int) * m); //A
    int *b = malloc(sizeof(int) * m); //B
    int *c = malloc(sizeof(int) * m); //C
    assert(a && b && c);
    for (size_t j = 0; j < m; j++)
    {
        scanf("%d%d%d", &a[j], &b[j], &c[j]);
    }
    printf("%lld\n", costruisci(n, m, h, a, b, c));
    free(c);
    free(b);
    free(a);
    free(h);
    return 0;
}
