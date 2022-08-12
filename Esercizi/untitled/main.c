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

void createEmployeesFile(Person *persons, int len) {
    FILE *file;
    file = fopen("impiegati.dat", "wb");
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

void readEmployeesFile(Person *employees) {
    FILE *file;
    Person employee;
    file = fopen("impiegati.dat", "rb");
    if (!ferror(file)) {
        int n;
        fread(&n, sizeof(int), 1, file);

        if (employees != NULL)
            free(employees);
        employees = (Person *) malloc(n * sizeof(Person));
        printf("users:%d\n", n);
        for (int i = 0; i < n; ++i) {
            fread(&employees[i], sizeof(Person), 1, file);
            printf("ID: %d, Name: %s, Surname: %s, Address: %s, Salar: %.2lf \n", employees[i].id, employees[i].name,
                   employees[i].surname, employees[i].address, employees[i].salar);
        }

        fclose(file);
    } else {
        printf("Error during file opening!\n");
    }
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
        createEmployeesFile(employees, n);
        printf("File updated!\n");
    }
}

Person *addEmployee(Person *list) {
    Person newUser;
    printf("Insert id:");
    scanf("%d", &newUser.id);
    printf("Insert name:");
    char s[20];
    scanf("%s", s);
    strcpy(newUser.name, s);
    printf("Insert surname:");
    scanf("%s", s);
    strcpy(newUser.surname, s);
    printf("Insert address:");
    scanf("%s", s);
    strcpy(newUser.address, s);

    printf("Insert salar:");
    scanf("%lf", &newUser.salar);
    list = (Person *) realloc(list, ((sizeof(list) / sizeof(Person)) + 1) * sizeof(Person));
    list[(sizeof(list) / sizeof(Person))] = newUser;
    printf("New employee registered!\n");
    return list;
}

int main() {
    Person *employees;
    employees = addEmployee(employees);
    printf("dim %d\n",sizeof(*employees) / sizeof(Person));

    printf("add new onece   \n");
    employees = addEmployee(employees);

    // addEmployee(employees);
    printf("dim %d\n",sizeof(*employees) / sizeof(Person));
    createEmployeesFile(employees, sizeof(employees) / sizeof(Person));
    readEmployeesFile(employees);
    //updateDataFromTxt(employees, 5, "update.txt");
    return 0;
}
