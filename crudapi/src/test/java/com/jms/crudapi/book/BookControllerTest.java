package com.jms.crudapi.book;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book book;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        Long id = 1L;
        String title = "Hansel and Gretel";
        String author = "Grim Bros";
        book = new Book(id, title, author);

        // MockitoAnnotations.initMocks(this);
        // this.mockMvc =
        // MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> bookList = Collections.singletonList(book);
        when(bookService.getAllBooks()).thenReturn(bookList);

        mockMvc.perform(get("/getAllBooks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void testGetBookById() throws Exception {
        when(bookService.getBookById(book.getId())).thenReturn(book);

        mockMvc.perform(get("/getBookById/" + book.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testAddBook() throws Exception {
        when(bookService.addBook(book)).thenReturn(book);

        mockMvc.perform(
            post("/addBook")
            .content(objectMapper.writeValueAsString(book))
            .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateBook() throws Exception {
        when(bookService.updateBook(book.getId(), book)).thenReturn(book);
        
        // edit title
        String title = "this is edited title";
        book.setTitle(title);

        mockMvc.perform(
            post("/updateBookById/" + book.getId())
            .content(objectMapper.writeValueAsString(book))
            .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBook() throws Exception {
        when(bookService.deleteBook(book.getId())).thenReturn(true);
        mockMvc.perform(delete("/deleteBookById/" + book.getId()))
            .andDo(print())
            .andExpect(status().isOk());
    }

}
