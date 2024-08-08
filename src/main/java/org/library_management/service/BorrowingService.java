package org.library_management.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BorrowingService {

    private Map<Long, Long> borrowedBooks = new HashMap<>();

    @Transactional
    public boolean borrowBook(Long bookId, Long patronId) {
        if (borrowedBooks.containsKey(bookId)) {
            // Book is already borrowed
            return false;
        }
        // Mark the book as borrowed by the patron
        borrowedBooks.put(bookId, patronId);
        return true;
    }

    @Transactional
    public boolean returnBook(Long bookId, Long patronId) {
        if (borrowedBooks.containsKey(bookId) && borrowedBooks.get(bookId).equals(patronId)) {
            // The patron is returning a book they borrowed
            borrowedBooks.remove(bookId);
            return true;
        }
        // The book is not borrowed by this patron
        return false;
    }
}

