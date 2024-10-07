"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// src/main.ts
const user_1 = require("./models/user");
const book_1 = require("./models/book");
const library_1 = require("./services/library");
const storage_1 = require("./services/storage");
const libraryservices_1 = require("./services/libraryservices");
const validation_1 = require("./utils/validation");
const bookStorage = new storage_1.LocalStorageService('books');
const userStorage = new storage_1.LocalStorageService('users');
// Load existing data from LocalStorage
bookStorage.load().forEach(book => library_1.bookLibrary.add(book));
userStorage.load().forEach(user => library_1.userLibrary.add(user));
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
    if (!(0, validation_1.validateYear)(year)) {
        alert('Invalid year. Please enter a valid year.');
        return;
    }
    // src/main.ts
    const newBook = new book_1.Book(Date.now(), title, author, year);
    library_1.bookLibrary.add(newBook);
    bookStorage.save(library_1.bookLibrary.getAll());
    updateBookList(); // Refresh list after adding book
});
// Add user
const addUserForm = document.getElementById('addUserForm');
addUserForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const name = document.getElementById('userName').value;
    const email = document.getElementById('userEmail').value;
    // src/main.ts
    const newUser = new user_1.User(Date.now(), name, email);
    library_1.userLibrary.add(newUser);
    userStorage.save(library_1.userLibrary.getAll());
    updateUserList(); // Refresh list after adding user
});
// Borrow Book
const borrowBookForm = document.getElementById('borrowBookForm');
borrowBookForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const userId = parseInt(document.getElementById('borrowUserId').value);
    const bookTitle = document.getElementById('borrowBookTitle').value;
    const result = (0, libraryservices_1.borrowBook)(userId, bookTitle);
    alert(result);
    // Save updated libraries to local storage
    bookStorage.save(library_1.bookLibrary.getAll());
    userStorage.save(library_1.userLibrary.getAll());
    updateBookList();
    updateUserList();
});
// Return Book
const returnBookForm = document.getElementById('returnBookForm');
returnBookForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const userId = parseInt(document.getElementById('returnUserId').value);
    const bookTitle = document.getElementById('returnBookTitle').value;
    const result = (0, libraryservices_1.returnBook)(userId, bookTitle);
    alert(result);
    // Save updated libraries to local storage
    bookStorage.save(library_1.bookLibrary.getAll());
    userStorage.save(library_1.userLibrary.getAll());
    updateBookList();
    updateUserList();
});
// Update book list dynamically
function updateBookList() {
    const bookList = document.getElementById('bookList');
    bookList.innerHTML = ''; // Clear existing content
    const books = library_1.bookLibrary.getAll();
    if (books.length === 0) {
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
// Update user list dynamically
function updateUserList() {
    const userList = document.getElementById('userList');
    userList.innerHTML = ''; // Clear existing content
    const users = library_1.userLibrary.getAll();
    if (users.length === 0) {
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
// Initial update of lists
updateBookList();
updateUserList();
