"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.borrowBook = borrowBook;
exports.returnBook = returnBook;
const library_1 = require("./library");
function borrowBook(userId, bookTitle) {
    const user = library_1.userLibrary.find(user => user.id === userId);
    const book = library_1.bookLibrary.find(book => book.title === bookTitle && !book.isBorrowed);
    if (user && book) {
        if (user.borrowedBooks < 3) {
            book.isBorrowed = true;
            user.borrowedBooks += 1;
            return `Book '${bookTitle}' borrowed successfully by User ID: ${userId}!`;
        }
        else {
            return 'User cannot borrow more than 3 books.';
        }
    }
    else {
        return 'Either the book is not available or the user ID is incorrect.';
    }
}
function returnBook(userId, bookTitle) {
    const user = library_1.userLibrary.find(user => user.id === userId);
    const book = library_1.bookLibrary.find(book => book.title === bookTitle && book.isBorrowed);
    if (user && book) {
        book.isBorrowed = false;
        user.borrowedBooks -= 1;
        return `Book '${bookTitle}' returned successfully by User ID: ${userId}!`;
    }
    else {
        return 'Either the book was not borrowed or the user ID is incorrect.';
    }
}
