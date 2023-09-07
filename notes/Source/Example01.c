#include <unistd.h>
#include <stdio.h>
int main(){
//Si stanno usando primitive di sistema

    printf("Start\n");
    int x;
    x=fork();//clona il processo ed esegui il codice nella nuova istanza dall'struzione successiva a fork

    pid_t pid = getpid();//prendi il pid assegnato
    pid_t  parentPID = getppid();//prende l'id del processo padra, in questo caso la bash se il padre, il figlio avrà il ppid del padre

    if(x==0){
        printf("Codice figlio ");
        printf("PID: %d ",pid);
        printf("PPID: %d ",parentPID);
        printf("fork_code:%d\n",x);
        execl("/usr/bin/date","date",0,NULL);//sostituisce la parte di memoria con il codice del execl
        printf("Dopo");//Non verrà mai eseguito
    }else
    {
        printf("Codice padre ");
        printf("PID: %d ",pid);
        printf("PPID: %d ",parentPID);
        printf("fork_code:%d\n",x);
    }

}