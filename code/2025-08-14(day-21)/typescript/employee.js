function printEmployees(employees) {
    employees.forEach(function (emp) {
        console.log("ID: ".concat(emp.empId, ", Name: ").concat(emp.empName, ", Salary: ").concat(emp.salary));
    });
    return employees.length;
}
var employees = [
    { empId: 1, empName: "chandu", salary: 50000 },
    { empId: 2, empName: "sekhar", salary: 60000 },
    { empId: 3, empName: "reddy", salary: 55000 }
];
var count = printEmployees(employees);
console.log("Total Employees: ".concat(count));
