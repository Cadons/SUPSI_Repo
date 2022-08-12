//
// Created by cadons on 01/04/22.
//
#include "Employee.h"
#include <iostream>
#include "Employee.h"
class Lecturer : public Employee {
public:
    Lecturer(string name, string
    institute, int nr,
                       string studies, string
                       course)
            : Employee(),
              m_studies{studies}, m_course{course} {
        cout << "Costructing Lecturer" << endl;
    }

    ~Lecturer() {
        cout << "Destroying Lecturer" << endl;
    }

    string studies() const {
        return m_studies;
    }

    string course() const {
        return m_course;
    }

    string classname() const override{
        return "Lecturer"+Employee::classname();
    }

protected:
    string m_studies;
    string m_course;
};

void application() {
    Employee c{};
    Lecturer d{"John", "ISIN", 42, "Computer Science",
               "Languages and frameworks"};
    Employee* pd{&d};
    Employee& rd{d};
    cout << c.classname() << ", " << d.classname()<< endl;
    }
namespace test{
    void foo(){
        std::cout<<"hello foo test"<<endl;
    }
    void foo2()
    {
        std::cout<<"hello foo 2"<<endl;

    }
}
void foo(){
    std::cout<<"hello foo global"<<endl;

}
int t(int ,double);
int t(int a,double s=0)
{

}
int& testRef(int&a,int&b)
{
    int tmp;
    if(a>b)
        return a;
    else
        return b;
    return tmp;
}
int main() {
    /*   test::foo();

      cout<<str.find("world",0)<<endl;*/
    int&& x=3,y=2;
    cout<<"vwd)"<<to_string(testRef(x,y))<<endl;
    //application(); }
}
