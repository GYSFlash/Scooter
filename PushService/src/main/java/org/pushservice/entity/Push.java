package org.pushservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Table(name = "push")
@Entity
public class Push {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @Column(name = "message_id")
    private Message message;
    @Column(name = "user_id")
    private UUID user_id;
    public Push(Message message, UUID user_id) {
        this.message = message;
        this.user_id = user_id;
    }
}
