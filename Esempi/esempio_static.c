#include <stdio.h>
void test()
{
    static int cnt=0;
    printf("%d\n",cnt);
    cnt++;

}
int main(int argc, char const *argv[])
{
    test();
    test();
    test();
    return 0;
}
