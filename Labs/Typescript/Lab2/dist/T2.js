"use strict";
class Circle {
    constructor(radius) {
        this.radius = radius;
    }
    getArea() {
        return Math.PI * this.radius * this.radius;
    }
    getPerimeter() {
        return 2 * Math.PI * this.radius;
    }
    scale(factor) {
        this.radius *= factor;
    }
}
class Rectangle {
    constructor(width, height) {
        this.width = width;
        this.height = height;
    }
    getArea() {
        return this.width * this.height;
    }
    getPerimeter() {
        return 2 * (this.width + this.height);
    }
    scale(factor) {
        this.width *= factor;
        this.height *= factor;
    }
}
class Triangle {
    constructor(sideA, sideB, sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }
    getArea() {
        const s = (this.sideA + this.sideB + this.sideC) / 2;
        return Math.sqrt(s * (s - this.sideA) * (s - this.sideB) * (s - this.sideC));
    }
    getPerimeter() {
        return this.sideA + this.sideB + this.sideC;
    }
    scale(factor) {
        this.sideA *= factor;
        this.sideB *= factor;
        this.sideC *= factor;
    }
}
const shapes = [
    new Circle(5),
    new Rectangle(4, 6),
    new Triangle(3, 4, 5)
];
let totalArea = 0;
let totalPerimeter = 0;
shapes.forEach(shape => {
    totalArea += shape.getArea();
    totalPerimeter += shape.getPerimeter();
});
console.log(`Total Area: ${totalArea}`);
console.log(`Total Perimeter: ${totalPerimeter}`);
