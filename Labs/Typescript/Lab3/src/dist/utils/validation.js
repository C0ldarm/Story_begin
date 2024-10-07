"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.validateUserId = validateUserId;
exports.validateYear = validateYear;
// src/utils/validation.ts
function validateUserId(userId) {
    return Number.isInteger(userId) && userId > 0; // Simple validation example
}
function validateYear(year) {
    return Number.isInteger(year) && year > 0; // Check if year is a positive integer
}
