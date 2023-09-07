#include <iostream>
#include "Number.h"

using namespace std;
int main()
{
    double a, b;
    std::cout << "First number: ";
    cin >> a;
    std::cout << "Second number: ";
    cin >> b;
    Number n1{ a };
    Number n2{ b };

    std::cout << n1.GetValue() << "+" << n2.GetValue() << "=" << n1.GetValue() + n2.GetValue() << endl;

}