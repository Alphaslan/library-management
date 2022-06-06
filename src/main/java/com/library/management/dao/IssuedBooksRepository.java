package com.library.management.dao;

import com.library.management.entities.IssuedBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedBooksRepository extends JpaRepository<IssuedBooks, Integer> {

}
