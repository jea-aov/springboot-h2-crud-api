package com.jms.crudapi.book;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jms.crudapi.book.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // getBookById
    public Book getBookById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isPresent()) {
            return book.get();
        }

        throw new ResourceNotFoundException("Book not found");
    }

    // addBook
    public Book addBook(Book newBook) throws Exception {
        if (newBook.getTitle() == null) {
            throw new Exception("Title is null");
        }

        if (newBook.getAuthor() == null) {
            throw new Exception("Author is null");
        }

        Book bookObj = bookRepository.save(newBook);
        return bookObj;

    }

    // updateBookById
    public Book updateBook(Long bookId, Book newBook) {
        Optional<Book> oldBook = bookRepository.findById(bookId);

        if (oldBook.isPresent()) {
            Book updatedBook = oldBook.get();
            updatedBook.setTitle(newBook.getTitle());
            updatedBook.setAuthor(newBook.getAuthor());

            Book bookObj = bookRepository.save(updatedBook);

            return bookObj;
        }

        throw new ResourceNotFoundException("Book not found");
    }

    // deleteBookById
    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new ResourceNotFoundException("Book not found");
        }

        bookRepository.deleteById(bookId);
    }

}
