package com.ch_vadim.todoServer.store.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "chat_id")
    long chatId;

    String name;

    @Nullable
    String email;

    @Builder.Default
    NotificationState notificationState = NotificationState.NOTHING;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TaskEntity> tasks = new ArrayList<>();


}
