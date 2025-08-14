
class Box<T> {
    value: T;

    constructor(value: T) {
        this.value = value;
    }

    getValue(): T {
        return this.value;
    }
}
let numberBox = new Box<number>(42);
let stringBox = new Box<string>("Hello TypeScript");
let booleanBox = new Box<boolean>(true);

console.log(numberBox.getValue());   
console.log(stringBox.getValue());   
console.log(booleanBox.getValue());  
