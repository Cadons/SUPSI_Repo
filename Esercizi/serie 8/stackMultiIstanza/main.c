#include <stdio.h>
#include "stack.h"
int main() {
    printf("Hello, Stack!\n");
    Stack * stack1=createStack();
    Stack * stack2=createStack();
    for (int i = 0; i < 5; ++i) {
        push(stack1,i);

        push(stack2,i*2);
    }
    for (int i = 0; i < 5; ++i) {
        printf("pop1:%d\n", pop(stack1));
        printf("pop2:%d\n", pop(stack2));
        printf("---------------\n");
    }
    return 0;
}
