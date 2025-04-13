"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Book = void 0;
class Book {
    constructor(id, title, author, year, isBorrowed = false) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isBorrowed = isBorrowed;
    }
}
exports.Book = Book;
