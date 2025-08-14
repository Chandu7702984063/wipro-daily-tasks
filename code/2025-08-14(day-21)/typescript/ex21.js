var Box = /** @class */ (function () {
    function Box(value) {
        this.value = value;
    }
    Box.prototype.getValue = function () {
        return this.value;
    };
    return Box;
}());
var numberBox = new Box(42);
var stringBox = new Box("Hello TypeScript");
var booleanBox = new Box(true);
console.log(numberBox.getValue());
console.log(stringBox.getValue());
console.log(booleanBox.getValue());
