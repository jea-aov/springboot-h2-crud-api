package com.jms.crudapi.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jms.crudapi.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>{

}
