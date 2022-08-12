void init_Timer2(int mode, int T, unsigned long freq, int presc);
void resetTimer2();
int awaitT2();
void startTimer2();
void stopTimer2();
void T2setInterruptPriority(int priority, int sub_priority);
void changePR(int T, int freq, int presc);