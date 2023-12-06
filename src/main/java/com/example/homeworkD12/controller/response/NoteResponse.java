package com.example.homeworkD12.controller.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponse {

    private UUID id;
    private String title;
    private String content;
}
