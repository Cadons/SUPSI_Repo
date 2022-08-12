#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct person {
    int id;
    char name[20];
    char *surname[20];
    char *address[20];
    double salar;
} Person;

void createEmployeesFile(Person *persons, int len,char * filename) {
    FILE *file;
    file = fopen(filename, "wb");
    if (!ferror(file)) {
        fwrite(&len, sizeof(int), 1, file);//dim
        for (int i = 0; i < len; ++i) {
            fwrite(&persons[i], sizeof(Person), 1, file);
        }
        printf("File written\n");
        fclose(file);
    } else {
        printf("Error during file creation!\n");
    }
}

Person *readEmployeesFile(Person *employees, int *dim, char *filename) {
    FILE *file;
    Person employee;
    file = fopen(filename, "rb");
    if (!ferror(file)) {
        int n;
        fread(&n, sizeof(int), 1, file);
        n++;
        if (employees != NULL)
            free(employees);
        employees = (Person *) malloc(n * sizeof(Person));
        printf("users:%d\n", n);
        for (int i = 0; i < n - 1; ++i) {
            fread(&employees[i], sizeof(Person), 1, file);
            printf("ID: %d, Name: %s, Surname: %s, Address: %s, Salar: %.2lf \n", employees[i].id, employees[i].name,
                   employees[i].surname, employees[i].address, employees[i].salar);
        }
        *dim = n;
        fclose(file);
    } else {
        printf("Error during file opening!\n");
        employees = NULL;
    }
    return employees;
}

void updatePerson(Person *p, int id, int p_len, double value) {
    for (int i = 0; i < p_len; ++i) {
        if (p[i].id == id) {
            p[i].salar = value;
            printf("%s %s new salar is: %.2lf\n", p[i].name, p[i].surname, p[i].salar);
            return;
        }
    }
}

void updateDataFromTxt(Person *employees, int n, char *filename) {
    FILE *file = fopen(filename, "r");
    if (!ferror(file)) {
        do {
            int id;
            double newSalar;
            fscanf(file, "%d-%lf", &id, &newSalar);
            updatePerson(employees, id, n, newSalar);
        } while (!feof(file));
        fclose(file);
        printf("File updated!\n");
    }
}

Person *addEmployee(Person *list, int *n) {
    Person newUser;
    printf("Insert id:");
    scanf("%d", &newUser.id);
    getchar();
    printf("Insert name:");
    char s[20];
    scanf(" %s", &s);
    strcpy(newUser.name, s);
    printf("Insert surname:");
    scanf(" %s", &s);
    strcpy(newUser.surname, s);
    printf("Insert address:");
    scanf(" %[^\n]", &s);

    strcpy(newUser.address, s);

    printf("Insert salar:");
    scanf("%lf", &newUser.salar);


    printf("New employee registered!\n");
    if (*n > 0)
        list[(*n) - 1] = newUser;
    else
        list[0] = newUser;
    (*n)++;

    return *n>0?(Person *) realloc(list, (*n) * sizeof(Person)):(Person *) realloc(list, 2* sizeof(Person));


}

int menu() {
    printf("MENU\n"
           "1)Add employee\n"
           "2)Update from file\n"
           "3)Save\n"
           "4)Open\n"
           "5)Show loaded data\n"
           "0)Exit\n"
           "Select option:");
    int sel;
    scanf("%d", &sel);
    getchar();
    return sel;
}

int main() {

    Person *employees = malloc(1 * sizeof(Person));
    int sel = menu();
    int dim = 1;

    while (sel != 0) {
        char str[20] = "";

        switch (sel) {
            case 1:
                employees = addEmployee(employees, &dim);
                break;
            case 2:
                printf("Update file:");
                scanf(" %s", str);
                updateDataFromTxt(employees, dim - 1, str);
                break;
            case 3:
                printf("Filename:");
                scanf(" %s", str);
                createEmployeesFile(employees, dim - 1,str);
                break;
            case 4:
                printf("Filename:");
                scanf(" %s", str);
                employees=readEmployeesFile(employees, &dim, str);
                break;
            case 5:
                if (employees != NULL && dim > 0)
                    for (int i = 0; i < dim - 1; ++i) {
                        printf("ID: %d, Name: %s, Surname: %s, Address: %s, Salar: %.2lf \n", employees[i].id,
                               employees[i].name,
                               employees[i].surname, employees[i].address, employees[i].salar);
                    }
                break;
        }

        sel = menu();

    }
    return 0;
}
