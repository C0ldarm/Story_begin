// models.ts
interface IBook {
    title: string;
    author: string;
    year: number;
    isBorrowed: boolean;
}

interface IUser {
    id: number;
    name: string;
    email: string;
    borrowedBooks: number;
}

// Book class
class Book implements IBook {
    constructor(public title: string, public author: string, public year: number, public isBorrowed: boolean = false) {}
}

// User class
class User implements IUser {
    public borrowedBooks: number = 0;

    constructor(public id: number, public name: string, public email: string) {}
}

// Library class using Generics
class Library<T> {
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

// Storage class for LocalStorage operations
class LocalStorageService<T> {
    constructor(private key: string) {}

    save(data: T[]): void {
        localStorage.setItem(this.key, JSON.stringify(data));
    }

    load(): T[] {
        const storedData = localStorage.getItem(this.key);
        return storedData ? JSON.parse(storedData) : [];
    }

    clear(): void {
        localStorage.removeItem(this.key);
    }
}

// Main application logic with renamed LocalStorageService
const bookStorage = new LocalStorageService<Book>('books');
const userStorage = new LocalStorageService<User>('users');

const bookLibrary = new Library<Book>();
const userLibrary = new Library<User>();

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

    const newBook = new Book(title, author, year);
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

    const newUser = new User(Date.now(), name, email); // ID is timestamp
    userLibrary.add(newUser);
    userStorage.save(userLibrary.getAll());

    updateUserList();  // Refresh list after adding user
});

// Update book list
function updateBookList(): void {
    const bookList = document.getElementById('bookList') as HTMLUListElement;
    bookList.innerHTML = '';  // Clear existing content
    
    const books = bookLibrary.getAll();
    if (books.length === 0) {
        // Show placeholder text if no books
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

// Update user list
function updateUserList(): void {
    const userList = document.getElementById('userList') as HTMLUListElement;
    userList.innerHTML = '';  // Clear existing content

    const users = userLibrary.getAll();
    if (users.length === 0) {
        // Show placeholder text if no users
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
// Borrow Book Function
const borrowBookForm = document.getElementById('borrowBookForm') as HTMLFormElement;
borrowBookForm.addEventListener('submit', (event) => {
    event.preventDefault();

    // Get input values
    const userId = parseInt((document.getElementById('borrowUserId') as HTMLInputElement).value);
    const bookTitle = (document.getElementById('borrowBookTitle') as HTMLInputElement).value;

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
        } else {
            alert('User cannot borrow more than 3 books.');
        }
    } else {
        alert('Either the book is not available or the user ID is incorrect.');
    }
});

// Return Book Function
const returnBookForm = document.getElementById('returnBookForm') as HTMLFormElement;
returnBookForm.addEventListener('submit', (event) => {
    event.preventDefault();

    // Get input values
    const userId = parseInt((document.getElementById('returnUserId') as HTMLInputElement).value);
    const bookTitle = (document.getElementById('returnBookTitle') as HTMLInputElement).value;

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
    } else {
        alert('Either the book was not borrowed or the user ID is incorrect.');
    }
});

