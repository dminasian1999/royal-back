package com.example.contacts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Represents a contact entity stored in MongoDB.  Each contact is stored in the
 * {@code contacts} collection and contains a first name, surname, phone number
 * and email address.  An {@code id} field is included to uniquely identify
 * each document in the collection.  Validation annotations ensure that
 * required fields are present and that the email and phone number follow
 * reasonable formats.
 */
@Document(collection = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    /**
     * The unique identifier for a contact.  Spring Data will map this field to
     * the MongoDB {@code _id} field.  The type is String because MongoDB
     * identifiers are stored as ObjectId, which Spring Data will convert
     * transparently to and from a String representation.
     */
    @Id
    private String id;

    /**
     * The given name of the person.  Cannot be blank.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * The surname or family name of the person.  Cannot be blank.
     */
    @NotBlank(message = "Surname is required")
    private String surname;

    /**
     * A phone number.  We allow digits, plus, hyphen and spaces.  The regular
     * expression used here is simple and intended for demonstration; you may
     * wish to adopt a stricter pattern depending on your requirements.
     */
    @Pattern(
            regexp = "^[+]?[0-9][0-9\\s-]{7,}$",
            message = "Phone number must be valid"
    )
    private String phoneNumber;

    /**
     * An email address.  The {@link Email} annotation validates the format.
     */
    @Email(message = "Email address must be valid")
    private String email;
}
