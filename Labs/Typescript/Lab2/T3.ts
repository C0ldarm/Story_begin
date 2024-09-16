abstract class Car {
    protected make: string;
    protected model: string;
    protected year: number;
  
    constructor(make: string, model: string, year: number) {
      this.make = make;
      this.model = model;
      this.year = year;
    }
  
    abstract describe(): void;
  }
  
  class Tesla extends Car {
    private range: number;
  
    constructor(model: string, year: number, range: number) {
      super('Tesla', model, year);
      this.range = range;
    }
  
    describe() {
      console.log(`${this.make} ${this.model} (${this.year}) with range of ${this.range} km.`);
    }
  }
  
  class BMW extends Car {
    private engine: string;
  
    constructor(model: string, year: number, engine: string) {
      super('BMW', model, year);
      this.engine = engine;
    }
  
    describe() {
      console.log(`${this.make} ${this.model} (${this.year}) with ${this.engine} engine.`);
    }
  }
  
  class Audi extends Car {
    private isQuattro: boolean;
  
    constructor(model: string, year: number, isQuattro: boolean) {
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
  