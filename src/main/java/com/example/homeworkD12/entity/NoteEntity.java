package com.example.homeworkD12.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity {

    private UUID id;
    private String title;
    private String content;
}
