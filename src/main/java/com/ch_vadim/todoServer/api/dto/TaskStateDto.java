package com.ch_vadim.todoServer.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStateDto {

    private long id;

    private String name;

    private long ordinal;

    private String description;

}
