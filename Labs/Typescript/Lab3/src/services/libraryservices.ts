// src/services/libraryservices.ts
import { User } from '../models/user';
import { Book } from '../models/book';
import { bookLibrary, userLibrary } from './library';

export function borrowBook(userId: number, bookTitle: string): string {
    const user = userLibrary.find(user => user.id === userId);
    const book = bookLibrary.find(book => book.title === bookTitle && !book.isBorrowed);

    if (user && book) {
        if (user.borrowedBooks < 3) {
            book.isBorrowed = true;
            user.borrowedBooks += 1;
            return `Book '${bookTitle}' borrowed successfully by User ID: ${userId}!`;
        } else {
            return 'User cannot borrow more than 3 books.';
        }
    } else {
        return 'Either the book is not available or the user ID is incorrect.';
    }
}

export function returnBook(userId: number, bookTitle: string): string {
    const user = userLibrary.find(user => user.id === userId);
    const book = bookLibrary.find(book => book.title === bookTitle && book.isBorrowed);

    if (user && book) {
        book.isBorrowed = false;
        user.borrowedBooks -= 1;
        return `Book '${bookTitle}' returned successfully by User ID: ${userId}!`;
    } else {
        return 'Either the book was not borrowed or the user ID is incorrect.';
    }
}
