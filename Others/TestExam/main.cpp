#include <iostream>
using namespace std;
template<typename T, int s>
class Alfa{
public:
    Alfa(T x):xx{x}{

    };
    void get()const{
        cout<<xx<<" "<<s;
    }
private:
    T xx;
};
int main() {
    std::cout << "Hello, World!" << std::endl;
    int x=10;
    Alfa<double,4> xa{10./123};
    auto foo=[x](int y){
        return x*y;
    };
    auto foo2=[xa](){
        cout <<"\nbye"<<endl;
        xa.get();
    };
    cout<<foo(2);
    foo2();
    return 0;
}

