"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.userLibrary = exports.bookLibrary = exports.Library = void 0;
class Library {
    constructor() {
        this.items = [];
    }
    add(item) {
        this.items.push(item);
    }
    getAll() {
        return this.items;
    }
    find(predicate) {
        return this.items.find(predicate);
    }
    remove(predicate) {
        this.items = this.items.filter(item => !predicate(item));
    }
}
exports.Library = Library;
// Initialize libraries
exports.bookLibrary = new Library();
exports.userLibrary = new Library();
