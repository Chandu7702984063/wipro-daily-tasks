
function pair<T, U>(first: T, second: U): [T, U] {
    return [first, second];
}


let result1 = pair(42, "Hello");      
let result2 = pair(true, 99);        
let result3 = pair("TS", { x: 10 });   

console.log(result1); 
console.log(result2); 
console.log(result3); 
