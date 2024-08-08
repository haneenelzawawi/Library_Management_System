package org.library_management.service;
import org.library_management.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public List<Book> getAllBooks() {
        return books;
    }

    public Optional<Book> getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    @Transactional
    public Book addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    @Transactional
    public Optional<Book> updateBook(Long id, Book bookDetails) {
        return getBookById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setIsbn(bookDetails.getIsbn());
            return book;
        });
    }

    @Transactional
    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }
}

