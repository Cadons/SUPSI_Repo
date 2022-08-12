#include <stdio.h>
#include <stdlib.h>
#include "stack.h"
#include <string.h>
#include <limits.h>

int isDigit(char n) {
    if (n >= 48 && n <= 57)
        return 1;
    else
        return 0;

}

char readString(char inputStr[], int *lastIndex) {
    char buffer[20] = "";
    if (strlen(inputStr) > 0) {
        for (int i = 0, j = 0; i < strlen(inputStr); ++i) {
            *lastIndex = i;
            if (isDigit(inputStr[i])) {
                buffer[j] = inputStr[i];
                j++;
            } else {
                if (inputStr[i] == '+' || inputStr[i] == '-' || inputStr[i] == '*' ||
                    inputStr[i] == '/') { return inputStr[i]; }
                else {
                    return '!';
                }
            }
            if (inputStr[i + 1] == ' ')
                return atoi(buffer);
            if (inputStr[i] == '\0')
                return '&';

        }
    } else {
        return '\0';
    }

}

int main() {
    printf("Insert postfix expression:");
    char data[20];
    int stop = 1;
    scanf("%20[^\n]", data);
    int index = 0;
    double output = 0;
    int b;
    int i, j, cnt = 0;
    int operationFlag = 0;
    while (stop) {
        char ris = readString(data, &index);

        switch (ris) {

            case '+':
                if (!operationFlag) {
                    pop();
                    operationFlag++;
                }
                output += pop();
                break;
            case '-':
                if (!operationFlag) {
                    pop();
                    operationFlag++;
                }
                output -= pop();
                break;
            case '*':
                if (!operationFlag) {
                    pop();
                    operationFlag++;
                }
                output *= pop();
                break;
            case '/':
                if (!operationFlag) {
                    pop();
                    operationFlag++;
                }
                b = pop();
                if (b == 0) {
                    printf("\nDivision for 0 not possible!\n");
                    output = INT_MIN;
                    stop = 0;
                } else {
                    output /= (double) b;
                }
                break;
            case '!'://not admited char
                printf("Character not valid!\n");
                stop = 0;
                break;
            case '&'://end string
                stop = 0;
                break;

            default:

                push(ris);
                if (!operationFlag)
                    output = ris;

                // printf("No accepted characters!\n")


                break;

        }


        for (i = index + 2, j = 0; data[i] != '\0'; ++i) {
            data[j] = data[i];
            j++;

        }
        if (data[1] == '\0')
            stop = 0;
        else {
            data[i - 1] = '\0';
            data[i - 2] = '\0';

            cnt++;//count iterations
        }

    }

    printf("\nResult:%lf", output);
    return 0;
}
