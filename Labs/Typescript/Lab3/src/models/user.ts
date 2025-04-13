// src/models/User.ts
export interface IUser {
  id: number;
  name: string;
  email: string;
  borrowedBooks: number;
}

export class User implements IUser {
  public borrowedBooks: number = 0;

  constructor(public id: number, public name: string, public email: string) {}
}
