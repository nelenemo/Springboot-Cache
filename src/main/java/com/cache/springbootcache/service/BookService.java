package com.cache.springbootcache.service;

import com.cache.springbootcache.dto.Book;

public interface BookService {
    Book addBook(Book book);

    Book updateBook(Book book);

    Book getBook(long id);

    String deleteBook(long id);
}
