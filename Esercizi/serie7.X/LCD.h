
#define LCDDATA 1 // RS = 1 ; access data register
#define LCDCMD 0 // RS = 0 ; access command register
#define PMDATA PMDIN // PMP data buffer
#define cmdLCD(c) writeLCD((LCDCMD),(c));
#define busyLCD() readLCD(LCDCMD)&0x80
#define putLCD(d) writeLCD((LCDDATA),(d))
#define clearLCD() writeLCD((LCDCMD),1)
void writeLCD(int addr, char c);
void putsLCD ( char *s);
char readLCD(int addr);
void LCDinit(int FPBCLK);
void Delayms(unsigned t);
void Timer2Handler(void);
