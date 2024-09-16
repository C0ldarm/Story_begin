"use strict";
class Cat {
    constructor(name, habitat) {
        this.name = name;
        this.habitat = habitat;
    }
    move() {
        console.log(`${this.name} walks.`);
    }
    sound() {
        console.log(`${this.name} says "Meow!"`);
    }
}
class Bird {
    constructor(name, habitat) {
        this.name = name;
        this.habitat = habitat;
    }
    move() {
        console.log(`${this.name} flies.`);
    }
    sound() {
        console.log(`${this.name} says "Tweet!"`);
    }
}
class Fish {
    constructor(name, habitat) {
        this.name = name;
        this.habitat = habitat;
    }
    move() {
        console.log(`${this.name} swims.`);
    }
}
