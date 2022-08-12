//
// Created by cadons on 01/04/22.
//

#ifndef LESSONSTESTS_EMPLOYEE_H
#define LESSONSTESTS_EMPLOYEE_H

#include <string>
using namespace std;

class Employee {
public:
    Employee();
    ~Employee();
    string name() const;
    string institute() const;
    int employeenr() const;
   virtual string classname() const;
protected:
    string m_name;
    string m_institute;
    int m_employeenr;

};


#endif //LESSONSTESTS_EMPLOYEE_H
