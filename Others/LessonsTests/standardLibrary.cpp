//
// Created by cadons on 25/03/22.
//
#include <string>
#include <iostream>
#include <sstream>
#include <vector>
#include <map>
#include <memory>
void strings()
{
    std::string msg{"Hello C++"};
    msg+="!";
    std::cout<<msg<<std::endl;

    std::stringstream ss;
    ss<<msg<<3.2f;
    std::cout<<ss.str()<<std::endl;
}
void take_int(int *x)
{
    // ...
}
void containers()
{
    //Vector
    std::vector<int> list;
    std::vector<int> v2{1,2,3,4,5};
    list.push_back(22);
    v2.pop_back();
    for(auto& x:v2){
        std::cout<<x<< std::endl;
    }
    std::cout<<"----\n";
    std::cout<<list.size()<<std::endl;
    std::cout<<list[0]<<std::endl;
    //Map
    std::map<std::string,int> myMap;
    myMap["element1"]=3;
    myMap["element1.4"]=3;

    myMap["element2"]=4;
    for(auto& X : myMap)
    {
        std::cout<<X.first<< " " <<X.second<<std::endl;
    }
    std::cout<<myMap.count("ads")<<std::endl;//return 0 not exist, 1 exist
    std::cout<<myMap.at("element1")<<std::endl;
    //Smart pointers
    auto i{new int(1)};
    take_int(i);

    std::vector<int*> v;
    v.push_back(i);//the pointer gets copied not the pointed value
//unique ptr
    std::unique_ptr<int> up{new int{13}};
    std::unique_ptr<int> up2{i};
    auto up3{std::make_unique<int>(45)};
    std::cout<<*up<<"\n";//unique_ptr can be dereferenced
    //shared ptr
    auto ssp{std::make_shared<int>(23)};
    auto ssp2=ssp;
    std::cout<<*ssp<<std::endl;

}
void exceptions(){
    int b=0;
    try{
        if(b==0)
            throw "b = 0 not possible";

        auto a=3/b;

        std::cout<<"3/0="<<a<<std::endl;

    }catch (...){
        std::cerr<<"error"<<std::endl;
        throw;//rethrown
    }
    try{
        if(b==0)
            throw 3;

        auto a=3/b;

        std::cout<<"3/0="<<a<<std::endl;

    }catch (int e){
        std::cerr<<e<<std::endl;

    }
    try{
        if(b==0)
            throw "b = 0 not possible";

        auto a=3/b;

        std::cout<<"3/0="<<a<<std::endl;

    }catch (const char * e){
        std::cerr<<e<<std::endl;

    }


}
/*void application(){
strings();
containers();
try{
    exceptions();

}catch (...)
{
    std::cerr<<"error from main"<<std::endl;
}
}
int main(){application();}*/