package org.library_management.service;
import org.library_management.model.Patron;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    private List<Patron> patrons = new ArrayList<>();
    private Long nextId = 1L;

    @Cacheable("parton")
    public List<Patron> getAllPatrons() {
        return patrons;
    }

    public Optional<Patron> getPatronById(Long id) {
        return patrons.stream().filter(patron -> patron.getId().equals(id)).findFirst();
    }

    @Transactional
    public Patron addPatron(Patron patron) {
        patron.setId(nextId++);
        patrons.add(patron);
        return patron;
    }

    @Transactional
    public Optional<Patron> updatePatron(Long id, Patron patronDetails) {
        return getPatronById(id).map(patron -> {
            patron.setFirstName(patronDetails.getFirstName());
            patron.setLastName(patronDetails.getLastName());
            patron.setEmail(patronDetails.getEmail());
            patron.setPhoneNumber(patronDetails.getPhoneNumber());
            return patron;
        });
    }

    @Transactional
    public boolean deletePatron(Long id) {
        return patrons.removeIf(patron -> patron.getId().equals(id));
    }
}

