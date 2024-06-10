package com.ch_vadim.todoServer.store.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.Instant;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "users_chat_id")
    private UserEntity user;

    @Builder.Default
    private TaskState state = TaskState.CREATED;

    @Builder.Default
    private Instant createdAt = Instant.now();
    @Nullable
    private Instant deadline;
}
