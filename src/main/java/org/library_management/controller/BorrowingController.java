package org.library_management.controller;


import org.library_management.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    // POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book.
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        boolean isBorrowed = borrowingService.borrowBook(bookId, patronId);
        if (isBorrowed) {
            return ResponseEntity.ok("Book borrowed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to borrow the book. It might already be borrowed.");
        }
    }

    // PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        boolean isReturned = borrowingService.returnBook(bookId, patronId);
        if (isReturned) {
            return ResponseEntity.ok("Book returned successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to return the book. It might not be borrowed.");
        }
    }
}

