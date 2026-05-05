package org.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
public class AuthorizationData {
    @Id
    @NonNull
    private String username;
    @NonNull
    private String password;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private Role role;

    public AuthorizationData(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.USER;
    }

    public enum Role {
        ADMIN, USER, EMPLOYEE
    }
}
