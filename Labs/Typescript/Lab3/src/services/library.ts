// src/services/library.ts
import { User } from '../models/user';
import { Book } from '../models/book';

export class Library<T> {
    private items: T[] = [];

    add(item: T): void {
        this.items.push(item);
    }

    getAll(): T[] {
        return this.items;
    }

    find(predicate: (item: T) => boolean): T | undefined {
        return this.items.find(predicate);
    }

    remove(predicate: (item: T) => boolean): void {
        this.items = this.items.filter(item => !predicate(item));
    }
}

// Initialize libraries
export const bookLibrary = new Library<Book>();
export const userLibrary = new Library<User>();
