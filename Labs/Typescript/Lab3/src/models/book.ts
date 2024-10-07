// src/models/Book.ts
export interface IBook {
  id: number; // Add id property
  title: string;
  author: string;
  year: number;
  isBorrowed: boolean;
}

export class Book implements IBook {
  constructor(public id: number, public title: string, public author: string, public year: number, public isBorrowed: boolean = false) {}
}
