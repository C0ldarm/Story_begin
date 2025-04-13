"use strict";
class Employee {
    constructor(name, age, salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    pay() {
        console.log(`${this.name} has been paid.`);
    }
}
class Developer extends Employee {
    getAnnualBonus() {
        return this.salary * 0.1;
    }
}
class Manager extends Employee {
    getAnnualBonus() {
        return this.salary * 0.2;
    }
}
// Масив співробітників
const employees = [
    new Developer('Alice', 25, 80000),
    new Manager('Bob', 35, 120000)
];
let totalBonus = 0;
employees.forEach(employee => {
    totalBonus += employee.getAnnualBonus();
});
console.log(`Total annual bonus: ${totalBonus}`);
