#include <proc/p32mx370f512l.h>

#include <p32xxxx.h>
static char r, g, b;

void initRGB() {
    ANSELDbits.ANSD1 = 0;
    ANSELDbits.ANSD3 = 0;
    TRISD = 0x0000;
}

void setRed() {

    LATDbits.LATD2 = 1;
    LATDbits.LATD12 = 0;
    LATDbits.LATD3 = 0;
    r = 1;
    g = 0;
    b = 0;

}

void setGreen() {

    LATDbits.LATD2 = 0;
    LATDbits.LATD12 = 1;
    LATDbits.LATD3 = 0;

    r = 0;
    g = 1;
    b = 0;
}

void setBlue() {

    LATDbits.LATD2 = 0;
    LATDbits.LATD12 = 0;
    LATDbits.LATD3 = 1;
    r = 0;
    g = 0;
    b = 1;

}

void switchOnRGBLed() {
    //reset to previous state
    LATDbits.LATD2 = r;
    LATDbits.LATD12 = g;
    LATDbits.LATD3 = b;
}

void switchOffRGBLed() {
    LATDbits.LATD2 = 0;
    LATDbits.LATD12 = 0;
    LATDbits.LATD3 = 0;
}

