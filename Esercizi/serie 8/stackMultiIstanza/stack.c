
#include "stack.h"
#include <stdlib.h>
#define MAX 20
//static int stack[MAX];
struct stackPrivate{
    int stack[MAX];
    short sp;
};
//static short sp = 0;



static int error=0;//0 no errors, 1 stack full, 2 stack void
Stack * createStack()
{
    Stack *stack=(Stack *) malloc(sizeof (Stack));
    stack->sp=0;
    return stack;
}

int errors()
{
    return  error;
}
static int full(Stack * stack)
{
    return stack->sp == MAX;
}
static int empty(Stack * stack)
{
    return stack->sp == 0;
}
void push(Stack* _stack_,int elt)
{
    if (!full(_stack_)) {
        _stack_->stack[_stack_->sp++] = elt;
    }else
    {
        //error
        error=1;
    }
}
int pop(Stack* _stack_)
{
    if(!empty(_stack_))
    {

        return _stack_->stack[--(_stack_->sp)];

    }else
    {
        //error
        error=2;

    }
}
int top(Stack* _stack_)
{
    if(!empty(_stack_))
    {
        return _stack_->stack[_stack_->sp-1];

    } else{
        //error

        error=2;
    }
}




