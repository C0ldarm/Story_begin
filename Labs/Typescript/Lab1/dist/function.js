"use strict";
// functions.ts
function greet(name, times = 1) {
    for (let i = 0; i < times; i++) {
        console.log(`Hello, ${name}!`);
    }
}
greet("Heisenbug");
greet("BSOD", 3);
