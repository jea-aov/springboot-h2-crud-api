package com.jms.crudapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jms.crudapi.model.Book;

public interface BookRepo extends JpaRepository<Book, Long>{

}
