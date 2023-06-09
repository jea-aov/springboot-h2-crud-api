package com.jms.crudapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jms.crudapi.book.Book;
import com.jms.crudapi.book.BookRepository;

@SpringBootTest
class CrudapiApplicationTests {
	BookRepository bookRepo;

	public CrudapiApplicationTests(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }
	
	@Test
	public void testCreate() {
		Book book = new Book();
		book.setId(1L);
		book.setTitle("Angels and Demons");
		book.setAuthor("Dan Brown");
		bookRepo.save(book);
		assertNotNull(bookRepo.findById(1L).get());

	}

}
