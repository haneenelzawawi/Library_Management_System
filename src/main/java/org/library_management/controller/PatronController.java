package org.library_management.controller;

import org.library_management.model.Patron;
import org.library_management.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    // GET /api/patrons: Retrieve a list of all patrons.
    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        List<Patron> patrons = patronService.getAllPatrons();
        return ResponseEntity.ok(patrons);
    }

    // GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Optional<Patron> patron = patronService.getPatronById(id);
        return patron.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/patrons: Add a new patron to the system.
    @PostMapping
    public ResponseEntity<Patron> addNewPatron(@RequestBody Patron patron) {
        Patron newPatron = patronService.addPatron(patron);
        return ResponseEntity.ok(newPatron);
    }

    // PUT /api/patrons/{id}: Update an existing patron's information.
    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @RequestBody Patron patronDetails) {
        Optional<Patron> updatedPatron = patronService.updatePatron(id, patronDetails);
        return updatedPatron.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /api/patrons/{id}: Remove a patron from the system.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        boolean isDeleted = patronService.deletePatron(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

