"use strict";
// Book class
class Book {
    constructor(title, author, year, isBorrowed = false) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isBorrowed = isBorrowed;
    }
}
// User class
class User {
    constructor(id, name, email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedBooks = 0;
    }
}
// Library class using Generics
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
// Storage class for LocalStorage operations
class LocalStorageService {
    constructor(key) {
        this.key = key;
    }
    save(data) {
        localStorage.setItem(this.key, JSON.stringify(data));
    }
    load() {
        const storedData = localStorage.getItem(this.key);
        return storedData ? JSON.parse(storedData) : [];
    }
    clear() {
        localStorage.removeItem(this.key);
    }
}
// Main application logic with renamed LocalStorageService
const bookStorage = new LocalStorageService('books');
const userStorage = new LocalStorageService('users');
const bookLibrary = new Library();
const userLibrary = new Library();
// Load existing data from LocalStorage
bookStorage.load().forEach(book => bookLibrary.add(book));
userStorage.load().forEach(user => userLibrary.add(user));
// Update lists when page is loaded
updateBookList();
updateUserList();
// Add book
const addBookForm = document.getElementById('addBookForm');
addBookForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const title = document.getElementById('bookTitle').value;
    const author = document.getElementById('bookAuthor').value;
    const year = parseInt(document.getElementById('bookYear').value);
    const newBook = new Book(title, author, year);
    bookLibrary.add(newBook);
    bookStorage.save(bookLibrary.getAll());
    updateBookList(); // Refresh list after adding book
});
// Add user
const addUserForm = document.getElementById('addUserForm');
addUserForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const name = document.getElementById('userName').value;
    const email = document.getElementById('userEmail').value;
    const newUser = new User(Date.now(), name, email); // ID is timestamp
    userLibrary.add(newUser);
    userStorage.save(userLibrary.getAll());
    updateUserList(); // Refresh list after adding user
});
// Update book list
function updateBookList() {
    const bookList = document.getElementById('bookList');
    bookList.innerHTML = ''; // Clear existing content
    const books = bookLibrary.getAll();
    if (books.length === 0) {
        // Show placeholder text if no books
        bookList.innerHTML = '<li class="list-group-item">No books available</li>';
    }
    else {
        books.forEach(book => {
            const li = document.createElement('li');
            li.className = 'list-group-item';
            li.textContent = `${book.title} by ${book.author} (${book.year}) - ${book.isBorrowed ? 'Borrowed' : 'Available'}`;
            bookList.appendChild(li);
        });
    }
}
// Update user list
function updateUserList() {
    const userList = document.getElementById('userList');
    userList.innerHTML = ''; // Clear existing content
    const users = userLibrary.getAll();
    if (users.length === 0) {
        // Show placeholder text if no users
        userList.innerHTML = '<li class="list-group-item">No users available</li>';
    }
    else {
        users.forEach(user => {
            const li = document.createElement('li');
            li.className = 'list-group-item';
            li.textContent = `${user.name} (ID: ${user.id}) - Books borrowed: ${user.borrowedBooks}`;
            userList.appendChild(li);
        });
    }
}
// Borrow Book Function
const borrowBookForm = document.getElementById('borrowBookForm');
borrowBookForm.addEventListener('submit', (event) => {
    event.preventDefault();
    // Get input values
    const userId = parseInt(document.getElementById('borrowUserId').value);
    const bookTitle = document.getElementById('borrowBookTitle').value;
    // Find the user and the book
    const user = userLibrary.find(user => user.id === userId);
    const book = bookLibrary.find(book => book.title === bookTitle);
    // Debugging information for visibility
    console.log("Borrowing book:");
    console.log("User:", user);
    console.log("Book:", book);
    // Check if user and book exist, and if the book is available
    if (user && book && !book.isBorrowed) {
        if (user.borrowedBooks < 3) {
            // Mark the book as borrowed
            book.isBorrowed = true;
            user.borrowedBooks += 1;
            // Save updated information
            bookStorage.save(bookLibrary.getAll());
            userStorage.save(userLibrary.getAll());
            // Update lists
            updateBookList();
            updateUserList();
            // Notify user of success
            alert(`Book '${bookTitle}' borrowed successfully by User ID: ${userId}!`);
        }
        else {
            alert('User cannot borrow more than 3 books.');
        }
    }
    else {
        alert('Either the book is not available or the user ID is incorrect.');
    }
});
// Return Book Function
const returnBookForm = document.getElementById('returnBookForm');
returnBookForm.addEventListener('submit', (event) => {
    event.preventDefault();
    // Get input values
    const userId = parseInt(document.getElementById('returnUserId').value);
    const bookTitle = document.getElementById('returnBookTitle').value;
    // Find the user and the book
    const user = userLibrary.find(user => user.id === userId);
    const book = bookLibrary.find(book => book.title === bookTitle);
    // Debugging information for visibility
    console.log("Returning book:");
    console.log("User:", user);
    console.log("Book:", book);
    // Check if user and book exist, and if the book is currently borrowed
    if (user && book && book.isBorrowed) {
        // Mark the book as available
        book.isBorrowed = false;
        user.borrowedBooks -= 1;
        // Save updated information
        bookStorage.save(bookLibrary.getAll());
        userStorage.save(userLibrary.getAll());
        // Update lists
        updateBookList();
        updateUserList();
        // Notify user of success
        alert(`Book '${bookTitle}' returned successfully by User ID: ${userId}!`);
    }
    else {
        alert('Either the book was not borrowed or the user ID is incorrect.');
    }
});
