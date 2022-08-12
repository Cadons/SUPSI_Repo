//
// Created by Cadons on 10/01/2022.
//

#ifndef _STACK_H_
#define _STACK_H_
typedef struct stackPrivate Stack;
Stack * createStack();;
void push(Stack* _stack_,int elt);
int pop(Stack* _stack_);
int top(Stack* _stack_);

int errors();
#endif //_STACK_H_
