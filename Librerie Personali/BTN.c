#include <p32xxxx.h>

void initBTN() {
    TRISBbits.TRISB1 = 1; // RB1 (BTNU) configured as input
    ANSELBbits.ANSB1 = 0; // RB1 (BTNU) disabled analog
    TRISBbits.TRISB0 = 1; // RB1 (BTNL) configured as input
    ANSELBbits.ANSB0 = 0; // RB1 (BTNL) disabled analog
    TRISFbits.TRISF4 = 1; // RF0 (BTNC) configured as input
    TRISBbits.TRISB8 = 1; // RB8 (BTNR) configured as input
    ANSELBbits.ANSB8 = 0; // RB8 (BTNR) disabled analog
    TRISAbits.TRISA15 = 1; // RA15 (BTND) configured as input
}

char getButtonPressedName() {

    if (PORTBbits.RB1)
        return 'U';
    else if (PORTBbits.RB0)
        return 'L';
    else if (PORTFbits.RF0)
        return 'C';
    else if (PORTBbits.RB8)
        return 'R';
    else if (PORTAbits.RA15)
        return 'D';
    else
        return 0;
}

int getButtonValue(char BtnName) {
    switch (BtnName) {
        case 'U':
            return PORTBbits.RB1;
        case 'L':
            return PORTBbits.RB0;
        case 'C':
            return PORTFbits.RF0;
        case 'R':
            return PORTBbits.RB8;
        case 'D':
            return PORTAbits.RA15;
        default:
            return 0;

    }
}
