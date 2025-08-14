// Ex 8: Using the spread operator to merge arrays

const arr1 = [1, 2, 3];
const arr2 = [4, 5, 6];
const newArr = [4, 5, ...arr1, 6, 7, ...arr2, 8, 9];

console.log(newArr); 

// ex 9 Function that sums three numbers
function sum(a, b, c) {
  return a + b + c;
}


const numbers = [1, 2, 3];
const result = sum(...numbers);
console.log(result); 

// ex10 Function to sum any number of arguments
function sumNumbers(...nums) {
  return nums.reduce((total, num) => total + num, 0);
}
console.log(sumNumbers(1, 2, 3, 4)); 
console.log(sumNumbers(5, 10));  


 // Ex11 Function to separate first element and rest of the arguments
function separateFirstRest(...args) {
  const [first, ...rest] = args; 
  return {
    first: first,
    rest: rest
  };
}

console.log(separateFirstRest(1, 2, 3, 4));

console.log(separateFirstRest("apple", "banana", "cherry"));

