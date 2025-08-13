function calculate(op) {
    const n1 = parseFloat(document.getElementById("num1").value);
    const n2 = parseFloat(document.getElementById("num2").value);
    let res = "";
    if (isNaN(n1) || isNaN(n2)) {
        res = "Please enter both numbers.";
    } else {
        switch(op) {
            case "add": res = n1 + n2; break;
            case "sub": res = n1 - n2; break;
            case "mul": res = n1 * n2; break;
            case "div": 
                res = n2 !== 0 ? (n1 / n2) : "Cannot divide by zero";
                break;
        }
    }
    document.getElementById("result").textContent = "Result: " + res;
}