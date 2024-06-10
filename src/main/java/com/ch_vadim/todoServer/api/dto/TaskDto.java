package com.ch_vadim.todoServer.api.dto;

import com.ch_vadim.todoServer.store.entities.TaskState;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private long id;

    @NotNull
    @JsonProperty("chat_id")
    private long chatId;

    private String name;

    @Nullable
    private Instant deadline;

    private TaskState state;
}
