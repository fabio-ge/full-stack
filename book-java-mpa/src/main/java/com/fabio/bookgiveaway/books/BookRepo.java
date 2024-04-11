package com.fabio.bookgiveaway.books;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookRepo  extends JpaRepository<BookModel,Long>{
    
}
