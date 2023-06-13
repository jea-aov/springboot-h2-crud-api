package com.jms.crudapi.student;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jms.crudapi.book.Book;
import com.jms.crudapi.book.BookRepository;
import com.jms.crudapi.book.BookService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetBookById() {
        // given
        String title = "Hansel and Gretel";
        String author = "Grim Bros";
        Book book = new Book(title, author); 

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Book bookObj = bookService.getBookById(1L);

        assertThat(bookObj).isEqualTo(book);
    }

    @Test
    @Disabled
    void testUpdateBook() throws Exception {
        // given
        String title = "Hansel and Gretel";
        String author = "Grim Bros";
        Book book = new Book(title, author);

        // when
        bookService.addBook(book);

        // then
        ArgumentCaptor<Book> bookArgs = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgs.capture());

        Book capturedBook = bookArgs.getValue();

        assertThat(capturedBook).isEqualTo(book);
    }

    @Test
    void testAddBook() throws Exception {
        // given
        String title = "Hansel and Gretel";
        String author = "Grim Bros";
        Book book = new Book(title, author);

        // when
        bookService.addBook(book);

        // then
        ArgumentCaptor<Book> bookArgs = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgs.capture());

        Book capturedBook = bookArgs.getValue();

        assertThat(capturedBook).isEqualTo(book);
    }

    
}
