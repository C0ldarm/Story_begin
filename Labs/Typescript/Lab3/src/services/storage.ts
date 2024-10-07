// src/services/storage.ts
export class LocalStorageService<T> {
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
