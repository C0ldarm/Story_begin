interface Animal {
    name: string;
    habitat: string;
    move(): void;
    sound?(): void; // опціональний метод, так як не всі тварини видають звуки
  }

  class Cat implements Animal {
    name: string;
    habitat: string;
  
    constructor(name: string, habitat: string) {
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
  
  class Bird implements Animal {
    name: string;
    habitat: string;
  
    constructor(name: string, habitat: string) {
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
  
  class Fish implements Animal {
    name: string;
    habitat: string;
  
    constructor(name: string, habitat: string) {
      this.name = name;
      this.habitat = habitat;
    }
  
    move() {
      console.log(`${this.name} swims.`);
    }
  }
  