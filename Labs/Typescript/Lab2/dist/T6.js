"use strict";
class Book {
    constructor(title, author, pages) {
        this.borrowed = false;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
    borrow() {
        this.borrowed = true;
    }
}
class Magazine {
    constructor(title, author, issueNumber) {
        this.borrowed = false;
        this.title = title;
        this.author = author;
        this.issueNumber = issueNumber;
    }
    borrow() {
        this.borrowed = true;
    }
}
class DVD {
    constructor(title, author, duration) {
        this.borrowed = false;
        this.title = title;
        this.author = author;
        this.duration = duration;
    }
    borrow() {
        this.borrowed = true;
    }
}
class Library {
    constructor() {
        this.items = [];
    }
    addItem(item) {
        this.items.push(item);
    }
    findItemByName(name) {
        return this.items.find(item => item.title === name);
    }
    listAvailableItems() {
        return this.items.filter(item => !item.borrowed);
    }
}
// Додавання та використання елементів
const library = new Library();
const book1 = new Book('The Great Gatsby', 'F. Scott Fitzgerald', 180);
const dvd1 = new DVD('Inception', 'Christopher Nolan', 148);
library.addItem(book1);
library.addItem(dvd1);
book1.borrow();
console.log(library.listAvailableItems());
