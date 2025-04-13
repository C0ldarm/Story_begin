"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.LocalStorageService = void 0;
// src/services/storage.ts
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
exports.LocalStorageService = LocalStorageService;
