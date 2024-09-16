interface LibraryItem {
    title: string;
    author: string;
    borrowed: boolean; // Додаємо властивість borrowed
  
    borrow(): void;
  }
  
  
  class Book implements LibraryItem {
    title: string;
    author: string;
    pages: number;
    borrowed: boolean = false;
  
    constructor(title: string, author: string, pages: number) {
      this.title = title;
      this.author = author;
      this.pages = pages;
    }
  
    borrow(): void {
      this.borrowed = true;
    }
  }
  
  class Magazine implements LibraryItem {
    title: string;
    author: string;
    issueNumber: number;
    borrowed: boolean = false;
  
    constructor(title: string, author: string, issueNumber: number) {
      this.title = title;
      this.author = author;
      this.issueNumber = issueNumber;
    }
  
    borrow(): void {
      this.borrowed = true;
    }
  }
  
  class DVD implements LibraryItem {
    title: string;
    author: string;
    duration: number;
    borrowed: boolean = false;
  
    constructor(title: string, author: string, duration: number) {
      this.title = title;
      this.author = author;
      this.duration = duration;
    }
  
    borrow(): void {
      this.borrowed = true;
    }
  }
  
  class Library {
    private items: LibraryItem[] = [];
  
    addItem(item: LibraryItem): void {
      this.items.push(item);
    }
  
    findItemByName(name: string): LibraryItem | undefined {
      return this.items.find(item => item.title === name);
    }
  
    listAvailableItems(): LibraryItem[] {
      return this.items.filter(item => !item.borrowed);
    }
  }
  
  // Додавання та використання елементів
  const library = new Library();
  const book1 = new Book('The Great Gatsby', 'F. Scott Fitzgerald', 180);
  const dvd1 = new DVD('Inception', 'Christopher Nolan', 148);
  
  library.addItem(book1);
  library.addItem(dvd1);
  
  book1.borrow();
  
  console.log(library.listAvailableItems());
  