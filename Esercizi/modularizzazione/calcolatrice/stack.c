//
// Created by Cadons on 10/01/2022.
//
//
// Created by Cadons on 23/12/2021.
//
#include "stack.h"
#define MAX 20
static int stack[MAX];
static short sp = 0;
static int error=0;//0 no errors, 1 stack full, 2 stack void
int errors()
{
    return  error;
}
static int full()
{
    return sp == MAX;
}
static int empty()
{
    return sp == 0;
}
void push(int elt)
{
    if (!full()) {
        stack[sp++] = elt;
    }else
    {
        //error
        error=1;
    }
}
int pop()
{
    if(!empty())
    {

        return stack[--sp];

    }else
    {
        //error
        error=2;

    }
}
int top()
{
    if(!empty())
    {
        return stack[sp-1];

    } else{
        //error

        error=2;
    }
}




