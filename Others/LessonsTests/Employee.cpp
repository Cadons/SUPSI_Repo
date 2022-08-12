//
// Created by cadons on 01/04/22.
//

#include "Employee.h"
#include <iostream>
Employee::Employee()

{
    cout << "Constructing Employee" << endl;
}
Employee::~Employee()
{
    cout << "Destroying Employee" << endl;
}
string Employee::name() const
{
    return m_name;
}
string Employee::institute() const
{
    return m_institute;
}
int Employee::employeenr() const
{
    return m_employeenr;
}
string Employee::classname() const
{
    return "Employee";
}