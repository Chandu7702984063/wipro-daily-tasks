
class BankAccount {
    constructor(accountNumber, balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    deposit(amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    withdraw(amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
        }
    }
}


class Vehicle {
    constructor(make, model, year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    getInfo() {
        return `${this.make} ${this.model} (${this.year})`;
    }
}

class Car extends Vehicle {
    constructor(make, model, year, numDoors) {
        super(make, model, year);
        this.numDoors = numDoors;
    }

    getInfo() {
        return `${super.getInfo()}, Doors: ${this.numDoors}`;
    }
}

const acc = new BankAccount('12345', 1000);
acc.deposit(500);
acc.withdraw(200);
console.log(acc.balance);

const vehicle = new Vehicle('Toyota', 'Corolla', 2020);
console.log(vehicle.getInfo());

const car = new Car('Honda', 'Civic', 2022, 4);
console.log(car.getInfo());