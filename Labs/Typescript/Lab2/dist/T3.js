"use strict";
class Car {
    constructor(make, model, year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
}
class Tesla extends Car {
    constructor(model, year, range) {
        super('Tesla', model, year);
        this.range = range;
    }
    describe() {
        console.log(`${this.make} ${this.model} (${this.year}) with range of ${this.range} km.`);
    }
}
class BMW extends Car {
    constructor(model, year, engine) {
        super('BMW', model, year);
        this.engine = engine;
    }
    describe() {
        console.log(`${this.make} ${this.model} (${this.year}) with ${this.engine} engine.`);
    }
}
class Audi extends Car {
    constructor(model, year, isQuattro) {
        super('Audi', model, year);
        this.isQuattro = isQuattro;
    }
    describe() {
        console.log(`${this.make} ${this.model} (${this.year}) is Quattro: ${this.isQuattro}.`);
    }
}
// Створення екземплярів
const teslaS = new Tesla('Model S', 2022, 600);
const bmwX5 = new BMW('X5', 2021, 'V6');
const audiA6 = new Audi('A6', 2020, true);
teslaS.describe();
bmwX5.describe();
audiA6.describe();
