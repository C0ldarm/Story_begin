import * as chai from 'chai';  // I
import * as mocha from 'mocha' ;
// import chai as a module
const expect = chai.expect;  
import { Library } from '../services/library';
import { Book } from '../models/book';

describe('Library Class', () => {
    let library: Library<Book>;

    beforeEach(() => {
        library = new Library<Book>();
    });

    it('should add a book to the library', () => {
        const book = new Book(Date.now(), 'Test Book', 'Test Author', 2022);
        library.add(book);
        const allBooks = library.getAll();
        expect(allBooks.length).to.equal(1);
        expect(allBooks[0].title).to.equal('Test Book');
    });

    it('should find a book by title', () => {
        const book = new Book(Date.now(), 'Unique Book', 'Test Author', 2022);
        library.add(book);
        const foundBook = library.find(b => b.title === 'Unique Book');
        expect(foundBook).to.not.be.undefined;
        expect(foundBook?.title).to.equal('Unique Book');
    });
});
