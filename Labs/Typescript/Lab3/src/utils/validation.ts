// src/utils/validation.ts
export function validateUserId(userId: number): boolean {
  return Number.isInteger(userId) && userId > 0; // Simple validation example
}

export function validateYear(year: number): boolean {
  return Number.isInteger(year) && year > 0; // Check if year is a positive integer
}
