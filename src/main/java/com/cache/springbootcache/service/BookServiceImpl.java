package com.cache.springbootcache.service;

import com.cache.springbootcache.dto.Book;
import com.cache.springbootcache.repo.BookRepo;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

@Service

public class BookServiceImpl implements BookService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override

    public Book addBook(Book book) {
        logger.info("add box of id " + book.getId());
//        book.setName(book.getName());
//        book.setCategory(book.getCategory());
//        book.setAuthor(book.getAuthor());
//        book.setPublisher(book.getPublisher());
//        book.setEdition(book.getEdition());
        return bookRepo.save(book);
    }

    @Override
    @CachePut (cacheNames = "books", key="#book.id")
    public Book updateBook(Book book) {
        bookRepo.updateAddress(book.getId(), book.getName());
        logger.info("update the id " + book.getId() + " with new name");
        return book;

    }

    @Override
    @Cacheable(cacheNames = "books", key = "#id")

    public Book getBook(long id) {
        logger.info("fetching book from db");
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            return new Book();
        }

    }

    @Override
//    @CacheEvict(cacheNames = "books", key = "#id")
    public String deleteBook(long id) {
        bookRepo.deleteById(id);
        logger.info("book is deleted from db");

        return "Book is deleted";
    }
}
