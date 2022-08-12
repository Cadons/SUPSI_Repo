#include <stdio.h>
#include "stack.h"
int main() {
    push(3);
    push(4);
    push(5);
    push(2);
    push(42);
    printf("top: %d\n",top());
    for (int i = 0; i < 5; ++i) {
        printf("pop: %d\n",pop());
    }

    return 0;
}
