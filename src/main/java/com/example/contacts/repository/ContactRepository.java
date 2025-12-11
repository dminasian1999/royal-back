package com.example.contacts.repository;

import com.example.contacts.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for {@link Contact} entities.  Extending
 * {@link MongoRepository} provides CRUD operations and query derivation
 * capabilities.  Spring will automatically generate a concrete implementation
 * at runtime.  The first generic parameter represents the domain type and the
 * second parameter is the type of the id field【570856736976237†L167-L185】.
 */
public interface ContactRepository extends MongoRepository<Contact, String> {
    // No additional methods are required for basic CRUD operations
}