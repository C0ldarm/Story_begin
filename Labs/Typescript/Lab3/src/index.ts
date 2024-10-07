// src/main.ts
import { User } from './models/user';
import { Book } from './models/book';
import { Library, bookLibrary, userLibrary } from './services/library';
import { LocalStorageService } from './services/storage';
import { borrowBook, returnBook } from './services/libraryservices';
import { validateUserId, validateYear } from './utils/validation';

const bookStorage = new LocalStorageService<Book>('books');
const userStorage = new LocalStorageService<User>('users');

// Load existing data from LocalStorage
bookStorage.load().forEach(book => bookLibrary.add(book));
userStorage.load().forEach(user => userLibrary.add(user));

// Update lists when page is loaded
updateBookList();
updateUserList();

// Add book
const addBookForm = document.getElementById('addBookForm') as HTMLFormElement;
addBookForm.addEventListener('submit', (event) => {
    event.preventDefault();
    
    const title = (document.getElementById('bookTitle') as HTMLInputElement).value;
    const author = (document.getElementById('bookAuthor') as HTMLInputElement).value;
    const year = parseInt((document.getElementById('bookYear') as HTMLInputElement).value);

    if (!validateYear(year)) {
        alert('Invalid year. Please enter a valid year.');
        return;
    }

// src/main.ts
const newBook = new Book(Date.now(), title, author, year);
    bookLibrary.add(newBook);
    bookStorage.save(bookLibrary.getAll());

    updateBookList();  // Refresh list after adding book
});

// Add user
const addUserForm = document.getElementById('addUserForm') as HTMLFormElement;
addUserForm.addEventListener('submit', (event) => {
    event.preventDefault();
    
    const name = (document.getElementById('userName') as HTMLInputElement).value;
    const email = (document.getElementById('userEmail') as HTMLInputElement).value;

 // src/main.ts
const newUser = new User(Date.now(), name, email);
    userLibrary.add(newUser);
    userStorage.save(userLibrary.getAll());

    updateUserList();  // Refresh list after adding user
});

// Borrow Book
const borrowBookForm = document.getElementById('borrowBookForm') as HTMLFormElement;
borrowBookForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const userId = parseInt((document.getElementById('borrowUserId') as HTMLInputElement).value);
    const bookTitle = (document.getElementById('borrowBookTitle') as HTMLInputElement).value;

    const result = borrowBook(userId, bookTitle);
    
    alert(result);

    // Save updated libraries to local storage
    bookStorage.save(bookLibrary.getAll());
    userStorage.save(userLibrary.getAll());

    updateBookList();
    updateUserList();
});

// Return Book
const returnBookForm = document.getElementById('returnBookForm') as HTMLFormElement;
returnBookForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const userId = parseInt((document.getElementById('returnUserId') as HTMLInputElement).value);
    const bookTitle = (document.getElementById('returnBookTitle') as HTMLInputElement).value;

    const result = returnBook(userId, bookTitle);
    
    alert(result);

    // Save updated libraries to local storage
    bookStorage.save(bookLibrary.getAll());
    userStorage.save(userLibrary.getAll());

    updateBookList();
    updateUserList();
});

// Update book list dynamically
function updateBookList(): void {
    const bookList = document.getElementById('bookList') as HTMLUListElement;
    bookList.innerHTML = '';  // Clear existing content
    
    const books = bookLibrary.getAll();
    if (books.length === 0) {
        bookList.innerHTML = '<li class="list-group-item">No books available</li>';
    } else {
        books.forEach(book => {
            const li = document.createElement('li');
            li.className = 'list-group-item';
            li.textContent = `${book.title} by ${book.author} (${book.year}) - ${book.isBorrowed ? 'Borrowed' : 'Available'}`;
            bookList.appendChild(li);
        });
    }
}

// Update user list dynamically
function updateUserList(): void {
    const userList = document.getElementById('userList') as HTMLUListElement;
    userList.innerHTML = '';  // Clear existing content

    const users = userLibrary.getAll();
    if (users.length === 0) {
        userList.innerHTML = '<li class="list-group-item">No users available</li>';
    } else {
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
