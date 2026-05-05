package org.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NonNull
    private String email;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    private boolean subscription;
    @NonNull
    private LocalDate dateOfBirth;

    public User(String email, String name, String surname, LocalDate dateOfBirth) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.subscription = false;
    }
}
