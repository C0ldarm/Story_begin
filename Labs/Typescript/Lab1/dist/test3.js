"use strict";
var IceCreamSize;
(function (IceCreamSize) {
    IceCreamSize[IceCreamSize["Small"] = 10] = "Small";
    IceCreamSize[IceCreamSize["Large"] = 25] = "Large"; // Великий стаканчик
})(IceCreamSize || (IceCreamSize = {}));
var Topping;
(function (Topping) {
    Topping[Topping["Chocolate"] = 5] = "Chocolate";
    Topping[Topping["Caramel"] = 6] = "Caramel";
    Topping[Topping["Berries"] = 10] = "Berries";
    Topping[Topping["Marshmallow"] = 5] = "Marshmallow"; // Маршмелоу
})(Topping || (Topping = {}));
function calculateIceCreamCost(size, toppings, withMarshmallow = false) {
    // Розрахунок вартості основи морозива
    let cost = size;
    // Додавання вартості начинок
    for (const topping of toppings) {
        cost += topping;
    }
    // Додавання вартості маршмелоу, якщо вибрано
    if (withMarshmallow) {
        cost += Topping.Marshmallow;
    }
    return cost;
}
// Вибір розміру морозива
const size = IceCreamSize.Large;
// Вибір начинок
const selectedToppings = [Topping.Chocolate, Topping.Berries];
// Вибір, чи додавати маршмелоу
const addMarshmallow = true;
// Розрахунок вартості
const totalCost = calculateIceCreamCost(size, selectedToppings, addMarshmallow);
console.log(`Total cost of ice cream: ${totalCost} UAH`);
