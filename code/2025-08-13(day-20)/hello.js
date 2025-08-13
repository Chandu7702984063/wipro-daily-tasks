// function add(a, b) {
//       console.log(a + b);
//     }
//     add("5", "10")
// document.getElementById("hello").innerHTML = "Hello, World!";

// function length() {
//     const inputLength = document.getElementById("hello").value.length;
//     document.getElementById("result").innerHTML = inputLength;
// }

// btn1 = document.getElementById("btn1");
// btn1.addEventListener("click", length);

// document.getElementById("hello").addEventListener("input", function() {
//     document.getElementById("remaining").innerHTML = 
//         (50 - this.value.length) + " characters are remaining";
// });

// let names = ["sekhar", "chandra", "Charlie","Suresh", "Ravi", "Ramu"];

// let filteredNames = names.filter(name => name.length > 5);
// console.log(filteredNames);
// let CapitalizedNames = filteredNames.map(name => name.toUpperCase());
// console.log(CapitalizedNames);


// let persons = [
//     { 
//         name: "Sekhar", 
//         age: 22, 
//         city: "Hyderabad" 
//     },
//     { 
//         name: "Chandra", 
//         age: 17, 
//         city: "Chennai" 
//     },
//     { name: "Charlie", 
//         age: 19, 
//         city: "Delhi" 
//     },
//     { 
//         name: "Suresh", 
//         age: 16, 
//         city: "Mumbai" 
//     },
    
// ];

// let filteredPersons = persons.filter(person => person.age > 18);
// console.log(filteredPersons);
// let employees = [
//     { name: "Alice", role: "Manager", empId: 101, salary: 80000 },
//     { name: "Bob", role: "Developer", empId: 102, salary: 60000 },
//     { name: "Charlie", role: "QA", empId: 103, salary: 50000 },
//     { name: "David", role: "Manager", empId: 104, salary: 85000 },
//     { name: "Eve", role: "Developer", empId: 105, salary: 62000 }
// ];

// let totalManagers = employees.filter(emp => emp.role == "Manager")
// let totalManagerSalary = totalManagers.reduce((sum, emp) => sum + emp.salary, 0);

// console.log(totalManagerSalary);
// let persons = [
//     { name: "Sekhar", age: 22, city: "Hyderabad" },
//     { name: "Chandra", age: 17, city: "Chennai" },
//     { name: "Charlie", age: 19, city: "Delhi" },
//     { name: "Suresh", age: 16, city: "Mumbai" },
//     { name: "Ravi", age: 25, city: "Bangalore" },
//     { name: "Ramu", age: 15, city: "Pune" }
// ];

// let personsWithStatus = persons.map(person => ({
//     ...person,
//     status: person.age >= 18 ? "Adult" : "Minor"
// }));
// console.log(personsWithStatus);

// let date = new Date(2030, 7, 13); 
// let days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
// let dayOfWeek = days[date.getDay()];
// console.log(dayOfWeek);

let str = "moom";
rev = ""
for(let i = str.length - 1; i >= 0; i--) {
    rev = rev + str[i];
}
if(rev === str) {
    console.log("palindrome");
}
else {
    console.log("not a palindrome");
}