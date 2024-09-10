enum IceCreamSize {
    Small = 10,
    Large = 20  
}

enum Topping {
    Chocolate = 5, 
    Caramel = 6,  
    Berries = 10,  
    Marshmallow = 5 
}

function calculateIceCreamCost(
    size: IceCreamSize,
    toppings: Topping[],
    withMarshmallow: boolean = false
): number {
 
    let cost = size;

    for (const topping of toppings) {
        cost += topping;
    }

    if (withMarshmallow) {
        cost += Topping.Marshmallow;
    }

    return cost;
}

const size = IceCreamSize.Large;

const selectedToppings = [Topping.Chocolate, Topping.Berries];

const addMarshmallow = true;

const totalCost = calculateIceCreamCost(size, selectedToppings, addMarshmallow);

console.log(`Total cost of ice cream: ${totalCost} UAH`);
