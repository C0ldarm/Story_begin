/*
 * ATTENTION: The "eval" devtool has been used (maybe by default in mode: "development").
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./src/index.ts":
/*!**********************!*\
  !*** ./src/index.ts ***!
  \**********************/
/***/ ((__unused_webpack_module, exports, __webpack_require__) => {

eval("\r\nvar _a, _b, _c, _d;\r\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\r\nconst book_1 = __webpack_require__(/*! ./models/book */ \"./src/models/book.ts\");\r\nconst user_1 = __webpack_require__(/*! ./models/user */ \"./src/models/user.ts\");\r\nconst libraryservices_1 = __webpack_require__(/*! ./services/libraryservices */ \"./src/services/libraryservices.ts\");\r\nconst storage_1 = __webpack_require__(/*! ./services/storage */ \"./src/services/storage.ts\");\r\nconst bookStorage = new storage_1.Storage('books');\r\nconst userStorage = new storage_1.Storage('users');\r\nconst notification = document.getElementById('notification');\r\n// Add Book\r\n(_a = document.getElementById('bookForm')) === null || _a === void 0 ? void 0 : _a.addEventListener('submit', (e) => {\r\n    e.preventDefault();\r\n    const title = document.getElementById('bookTitle').value;\r\n    const author = document.getElementById('bookAuthor').value;\r\n    const year = parseInt(document.getElementById('bookYear').value);\r\n    const newBook = new book_1.Book(Date.now(), title, author, year); // Use Date.now() as a unique ID for simplicity\r\n    const books = bookStorage.load();\r\n    bookStorage.save([...books, newBook]);\r\n    showNotification(`Book \"${newBook.title}\" added successfully!`, 'success');\r\n});\r\n// Add User\r\n(_b = document.getElementById('userForm')) === null || _b === void 0 ? void 0 : _b.addEventListener('submit', (e) => {\r\n    e.preventDefault();\r\n    const name = document.getElementById('userName').value;\r\n    const userId = parseInt(document.getElementById('userId').value);\r\n    const newUser = new user_1.User(userId, name);\r\n    const users = userStorage.load();\r\n    userStorage.save([...users, newUser]);\r\n    showNotification(`User \"${newUser.name}\" added successfully!`, 'success');\r\n});\r\n// Borrow Book\r\n(_c = document.getElementById('borrowForm')) === null || _c === void 0 ? void 0 : _c.addEventListener('submit', (e) => {\r\n    e.preventDefault();\r\n    const userId = parseInt(document.getElementById('borrowUserId').value);\r\n    const bookId = parseInt(document.getElementById('borrowBookId').value);\r\n    (0, libraryservices_1.borrowBook)(bookId, String(userId));\r\n});\r\n// Return Book\r\n(_d = document.getElementById('returnForm')) === null || _d === void 0 ? void 0 : _d.addEventListener('submit', (e) => {\r\n    e.preventDefault();\r\n    const userId = parseInt(document.getElementById('returnUserId').value);\r\n    const bookId = parseInt(document.getElementById('returnBookId').value);\r\n    (0, libraryservices_1.returnBook)(bookId, String(userId));\r\n});\r\n// Function to show notifications\r\nfunction showNotification(message, type) {\r\n    notification.innerText = message;\r\n    notification.className = `alert alert-${type}`;\r\n    notification.classList.remove('d-none');\r\n    setTimeout(() => {\r\n        notification.classList.add('d-none');\r\n    }, 3000);\r\n}\r\n\n\n//# sourceURL=webpack://library-management/./src/index.ts?");

/***/ }),

/***/ "./src/models/book.ts":
/*!****************************!*\
  !*** ./src/models/book.ts ***!
  \****************************/
/***/ ((__unused_webpack_module, exports) => {

eval("\r\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\r\nexports.Book = void 0;\r\nclass Book {\r\n    constructor(id, title, author, year, isBorrowed = false) {\r\n        this.id = id;\r\n        this.title = title;\r\n        this.author = author;\r\n        this.year = year;\r\n        this.isBorrowed = isBorrowed;\r\n    }\r\n    borrow() {\r\n        if (this.isBorrowed) {\r\n            throw new Error('Книга вже позичена');\r\n        }\r\n        this.isBorrowed = true;\r\n    }\r\n    return() {\r\n        this.isBorrowed = false;\r\n    }\r\n}\r\nexports.Book = Book;\r\n\n\n//# sourceURL=webpack://library-management/./src/models/book.ts?");

/***/ }),

/***/ "./src/models/user.ts":
/*!****************************!*\
  !*** ./src/models/user.ts ***!
  \****************************/
/***/ ((__unused_webpack_module, exports) => {

eval("\r\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\r\nexports.User = void 0;\r\nclass User {\r\n    constructor(id, name, borrowedBooks = []) {\r\n        this.id = id;\r\n        this.name = name;\r\n        this.borrowedBooks = borrowedBooks;\r\n        this.maxBorrowLimit = 3;\r\n    }\r\n    borrowBook(bookId) {\r\n        if (this.borrowedBooks.length >= this.maxBorrowLimit) {\r\n            throw new Error('Користувач не може позичити більше 3-х книг');\r\n        }\r\n        this.borrowedBooks.push(bookId);\r\n    }\r\n    returnBook(bookId) {\r\n        this.borrowedBooks = this.borrowedBooks.filter(id => id !== bookId);\r\n    }\r\n}\r\nexports.User = User;\r\n\n\n//# sourceURL=webpack://library-management/./src/models/user.ts?");

/***/ }),

/***/ "./src/services/libraryservices.ts":
/*!*****************************************!*\
  !*** ./src/services/libraryservices.ts ***!
  \*****************************************/
/***/ ((__unused_webpack_module, exports, __webpack_require__) => {

eval("\r\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\r\nexports.returnBook = exports.borrowBook = void 0;\r\nconst storage_1 = __webpack_require__(/*! ./storage */ \"./src/services/storage.ts\");\r\nconst bookStorage = new storage_1.Storage('books');\r\nconst userStorage = new storage_1.Storage('users');\r\n// Функція для позичання книги\r\nfunction borrowBook(bookId, userId) {\r\n    const books = bookStorage.load();\r\n    const users = userStorage.load();\r\n    // Перетворюємо id користувача на рядок для порівняння\r\n    const selectedUser = users.find(u => u.id.toString() === userId);\r\n    // Знаходимо книгу за її ID\r\n    const selectedBook = books.find(b => b.id === bookId);\r\n    // Знаходимо користувача за його ID\r\n    // Перевіряємо, чи книга і користувач знайдені\r\n    if (!selectedBook || !selectedUser) {\r\n        alert('Книга або користувач не знайдені.');\r\n        return;\r\n    }\r\n    // Перевіряємо, чи книга вже позичена\r\n    if (selectedBook.isBorrowed) {\r\n        alert(`Книга \"${selectedBook.title}\" вже позичена.`);\r\n        return;\r\n    }\r\n    // Перевіряємо, чи користувач позичив не більше 3 книг\r\n    if (selectedUser.borrowedBooks.length >= 3) {\r\n        alert(`${selectedUser.name} не може позичити більше 3-х книг.`);\r\n        return;\r\n    }\r\n    // Оновлюємо стан книги та користувача\r\n    selectedBook.borrow();\r\n    selectedUser.borrowBook(selectedBook.id);\r\n    // Зберігаємо оновлені дані\r\n    bookStorage.save(books);\r\n    userStorage.save(users);\r\n    alert(`Користувач ${selectedUser.name} успішно позичив книгу \"${selectedBook.title}\".`);\r\n}\r\nexports.borrowBook = borrowBook;\r\n// Функція для повернення книги\r\nfunction returnBook(bookId, userId) {\r\n    const books = bookStorage.load() || []; // Завантажуємо всі книги\r\n    const users = userStorage.load() || []; // Завантажуємо всіх користувачів\r\n    // Знаходимо книгу за її ID\r\n    const selectedBook = books.find(b => b.id === bookId);\r\n    // Знаходимо користувача за його ID\r\n    const selectedUser = users.find(u => u.id.toString() === userId);\r\n    // Перевіряємо, чи книга і користувач знайдені\r\n    if (!selectedBook || !selectedUser) {\r\n        alert('Книга або користувач не знайдені.');\r\n        return;\r\n    }\r\n    // Перевіряємо, чи книга була позичена\r\n    if (!selectedBook.isBorrowed) {\r\n        alert(`Книга \"${selectedBook.title}\" ще не була позичена.`);\r\n        return;\r\n    }\r\n    // Оновлюємо стан книги та користувача\r\n    selectedBook.return();\r\n    selectedUser.returnBook(selectedBook.id);\r\n    // Зберігаємо оновлені дані\r\n    bookStorage.save(books);\r\n    userStorage.save(users);\r\n    alert(`Користувач ${selectedUser.name} успішно повернув книгу \"${selectedBook.title}\".`);\r\n}\r\nexports.returnBook = returnBook;\r\n\n\n//# sourceURL=webpack://library-management/./src/services/libraryservices.ts?");

/***/ }),

/***/ "./src/services/storage.ts":
/*!*********************************!*\
  !*** ./src/services/storage.ts ***!
  \*********************************/
/***/ ((__unused_webpack_module, exports) => {

eval("\r\nObject.defineProperty(exports, \"__esModule\", ({ value: true }));\r\nexports.Storage = void 0;\r\nclass Storage {\r\n    constructor(key) {\r\n        this.storageKey = key;\r\n    }\r\n    save(items) {\r\n        localStorage.setItem(this.storageKey, JSON.stringify(items));\r\n    }\r\n    load() {\r\n        const data = localStorage.getItem(this.storageKey);\r\n        return data ? JSON.parse(data) : [];\r\n    }\r\n    clear() {\r\n        localStorage.removeItem(this.storageKey);\r\n    }\r\n}\r\nexports.Storage = Storage;\r\n\n\n//# sourceURL=webpack://library-management/./src/services/storage.ts?");

/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	
/******/ 	// startup
/******/ 	// Load entry module and return exports
/******/ 	// This entry module can't be inlined because the eval devtool is used.
/******/ 	var __webpack_exports__ = __webpack_require__("./src/index.ts");
/******/ 	
/******/ })()
;