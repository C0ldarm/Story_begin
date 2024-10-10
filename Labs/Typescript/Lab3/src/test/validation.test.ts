import { expect } from 'chai';  // Import expect from chai
import { validateYear } from '../utils/validation';

describe('Validation Functions', () => {
    it('should return true for valid years', () => {
        expect(validateYear(2022)).to.be.true;
        expect(validateYear(0)).to.be.false; 
        expect(validateYear(-5)).to.be.false; // Example of invalid year
    });
});
