package com.example.homeworkD12.service.service;

import com.example.homeworkD12.service.dto.NoteDto;
import com.example.homeworkD12.service.exception.NoteNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface NoteService {

    List<NoteDto> listAll();

    NoteDto add(NoteDto note);

    List<NoteDto> addAll(Collection<NoteDto> notes);

    void deleteById(UUID id) throws NoteNotFoundException;

    void update(NoteDto note) throws NoteNotFoundException;

    NoteDto getById(UUID id) throws NoteNotFoundException;
}
