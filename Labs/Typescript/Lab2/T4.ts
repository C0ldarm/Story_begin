interface Payable {
    pay(): void;
  }
  
  abstract class Employee implements Payable {
    protected name: string;
    protected age: number;
    protected salary: number;
  
    constructor(name: string, age: number, salary: number) {
      this.name = name;
      this.age = age;
      this.salary = salary;
    }
  
    abstract getAnnualBonus(): number;
  
    pay(): void {
      console.log(`${this.name} has been paid.`);
    }
  }
  
  class Developer extends Employee {
    getAnnualBonus(): number {
      return this.salary * 0.1;
    }
  }
  
  class Manager extends Employee {
    getAnnualBonus(): number {
      return this.salary * 0.2;
    }
  }
  
  // Масив співробітників
  const employees: Employee[] = [
    new Developer('Alice', 25, 80000),
    new Manager('Bob', 35, 120000)
  ];
  
  let totalBonus = 0;
  employees.forEach(employee => {
    totalBonus += employee.getAnnualBonus();
  });
  
  console.log(`Total annual bonus: ${totalBonus}`);
  