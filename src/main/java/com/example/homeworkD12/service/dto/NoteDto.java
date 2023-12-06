package com.example.homeworkD12.service.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {

    private UUID id;
    private String title;
    private String content;
}
