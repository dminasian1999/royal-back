package com.example.contacts.controller;

import com.example.contacts.model.Contact;
import com.example.contacts.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * REST controller that exposes CRUD endpoints for managing {@link Contact}
 * documents in MongoDB.  The base path for all endpoints is {@code /api/contacts}.
 */
@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*") // allow requests from any origin; adjust for production
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Retrieve all contacts.
     *
     * @return list of contacts
     */
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Retrieve a single contact by its identifier.
     *
     * @param id contact identifier
     * @return the corresponding contact or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id) {
        return contactRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
    }

    /**
     * Create a new contact.  The request body is validated using Bean Validation
     * annotations defined on the {@link Contact} class.
     *
     * @param contact contact to create
     * @return created contact with generated id
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@Valid @RequestBody Contact contact) {
        // Ensure the incoming contact has no id (or will be ignored by MongoDB)
        contact.setId(null);
        return contactRepository.save(contact);
    }

    /**
     * Update an existing contact.  If the contact does not exist, a 404 error
     * is returned.  Only non-null fields from the request body will overwrite
     * the existing values; this simple approach replaces the entire object.
     *
     * @param id      identifier of the contact to update
     * @param updated updated contact data
     * @return updated contact
     */
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable String id, @Valid @RequestBody Contact updated) {
        Contact existing = contactRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
        existing.setName(updated.getName());
        existing.setSurname(updated.getSurname());
        existing.setPhoneNumber(updated.getPhoneNumber());
        existing.setEmail(updated.getEmail());
        return contactRepository.save(existing);
    }

    /**
     * Delete a contact by id.
     *
     * @param id identifier of the contact
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable String id) {
        if (!contactRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
        }
        contactRepository.deleteById(id);
    }
}
