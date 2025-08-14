interface Employee {
  empId: number;
  empName: string;
  salary: number;
}
const employees: Employee[] = [
  { empId: 1, empName: "chandu", salary: 50000 },
  { empId: 2, empName: "sekhar", salary: 60000 },
  { empId: 3, empName: "reddy", salary: 55000 }
];

function printEmployees(employees: Employee[]): number {
  employees.forEach(emp => {
    console.log(`ID: ${emp.empId}, Name: ${emp.empName}, Salary: ${emp.salary}`);
  });
  return employees.length;
}

const count = printEmployees(employees);
console.log(`Total Employees: ${count}`);
