#include <stdio.h>
union reg
{
     int val;
     int val2;
    struct {
        unsigned char bit0:1;
        unsigned char bit1:1;
        unsigned char bit2:1;
        unsigned char bit3:1;
        unsigned char bit4:1;
        unsigned char bit5:1;
        unsigned char bit6:1;
        unsigned char bit7:1;
    };



};
typedef union reg Register;

int main(void) {
    Register r;
    r.val2 = 100;

    printf("Valore byte completo %d\n", r.val);
    printf("Valore byte completo %d\n", r.val2);
    printf("Valori singoli bit:\n");
    printf("b7: %d, b6: %d, b5: %d, b4: %d, b3: %d, b2: %d, b1: %d, b0: %d\n",
           r.bit7, r.bit6, r.bit5, r.bit4, r.bit3, r.bit2, r.bit1, r.bit0);

    return 0;
}